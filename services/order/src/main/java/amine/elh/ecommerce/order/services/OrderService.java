package amine.elh.ecommerce.order.services;


import amine.elh.ecommerce.order.clients.customer.CustomerClient;
import amine.elh.ecommerce.order.clients.payment.PaymentClient;
import amine.elh.ecommerce.order.clients.payment.dto.PaymentRequest;
import amine.elh.ecommerce.order.clients.product.ProductClient;
import amine.elh.ecommerce.order.dto.*;
import amine.elh.ecommerce.order.exceptions.BusinessException;
import amine.elh.ecommerce.order.kafka.OrderProducer;
import amine.elh.ecommerce.order.mapper.OrderMapper;
import amine.elh.ecommerce.order.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

        //check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(("Cannot create order The customer with id " + request.customerId() + " doesn't exist")));


        //purchase the products  --> product-ms (RestTemplate)
        var purchaseProduct =this.productClient.purchaseProducts(request.products());


        // persist order
        var order = this.orderRepository.save(mapper.toOrder(request));

        // persist orderlines
        for (PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //  start payment_process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);




        // send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProduct

                )
        );

        return order.getId();

    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow( () -> new EntityNotFoundException(String.format("Cannot find Order with id " + id)));
    }
}

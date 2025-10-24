package amine.elh.ecommerce.order.mapper;


import amine.elh.ecommerce.order.dto.OrderRequest;
import amine.elh.ecommerce.order.dto.OrderResponse;
import amine.elh.ecommerce.order.entities.Order;
import amine.elh.ecommerce.order.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}

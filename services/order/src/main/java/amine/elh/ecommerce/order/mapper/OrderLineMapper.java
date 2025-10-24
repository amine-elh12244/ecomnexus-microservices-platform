package amine.elh.ecommerce.order.mapper;


import amine.elh.ecommerce.order.dto.OrderLineRequest;
import amine.elh.ecommerce.order.dto.OrderLineResponse;
import amine.elh.ecommerce.order.entities.Order;
import amine.elh.ecommerce.order.entities.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                        .id(orderLineRequest.orderId())
                        .build()
                )
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {

        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}

package amine.elh.ecommerce.order.clients.payment.dto;

import amine.elh.ecommerce.order.clients.customer.dto.CustomerResponse;
import amine.elh.ecommerce.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}

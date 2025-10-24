package amine.elh.ecommerce.order.dto;

import amine.elh.ecommerce.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,

        String customerID
) {
}

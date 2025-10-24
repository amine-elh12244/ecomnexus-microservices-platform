package amine.elh.ecommerce.order.dto;


import amine.elh.ecommerce.order.clients.customer.dto.CustomerResponse;
import amine.elh.ecommerce.order.clients.product.dto.PurchaseResponse;
import amine.elh.ecommerce.order.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderRefernce,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}

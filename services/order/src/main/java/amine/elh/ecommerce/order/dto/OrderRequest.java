package amine.elh.ecommerce.order.dto;

import amine.elh.ecommerce.order.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,

        String reference,

        @Positive(message = "Order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "payment method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "CustomerId method should be present")
        @NotBlank(message = "CustomerId method should be present")
        @NotEmpty(message = "CustomerId method should be present")
        String customerId,

        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}

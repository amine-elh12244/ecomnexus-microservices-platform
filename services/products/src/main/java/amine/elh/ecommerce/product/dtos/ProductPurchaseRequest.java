package amine.elh.ecommerce.product.dtos;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Integer productId,

        @NotNull(message = "quantity is mandatory")
        double quantity
) {
}

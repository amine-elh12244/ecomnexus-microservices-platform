package amine.elh.ecommerce.product.exceptions;

import java.util.Map;

public record ErrorResponse (
        Map<String , String> errors
){}

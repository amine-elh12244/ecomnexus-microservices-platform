package amine.elh.ecommerce.order.exceptions;

import java.util.Map;

public record ErrorResponse (
        Map<String , String> errors
){}

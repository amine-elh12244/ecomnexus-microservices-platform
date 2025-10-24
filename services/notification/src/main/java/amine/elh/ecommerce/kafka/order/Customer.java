package amine.elh.ecommerce.kafka.order;

import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}

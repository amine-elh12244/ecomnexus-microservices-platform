package amine.elh.ecommerce.order.clients.customer.dto;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}

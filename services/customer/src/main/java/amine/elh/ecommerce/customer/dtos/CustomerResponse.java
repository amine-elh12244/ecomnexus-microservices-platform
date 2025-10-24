package amine.elh.ecommerce.customer.dtos;

import amine.elh.ecommerce.customer.documents.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}

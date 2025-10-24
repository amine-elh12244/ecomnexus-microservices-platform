package amine.elh.ecommerce.customer.controllers;

import amine.elh.ecommerce.customer.dtos.CustomerRequest;
import amine.elh.ecommerce.customer.dtos.CustomerResponse;
import amine.elh.ecommerce.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.existsById(customerId)) ;

    }


    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    ) {
        CustomerResponse customer = service.findById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") String customerId
    ) {
        service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}

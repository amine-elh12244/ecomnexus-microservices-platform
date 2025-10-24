package amine.elh.ecommerce.customer.services;

import amine.elh.ecommerce.customer.dtos.CustomerRequest;
import amine.elh.ecommerce.customer.dtos.CustomerResponse;
import amine.elh.ecommerce.customer.documents.Customer;
import amine.elh.ecommerce.customer.exceptions.CustomerNotFoundException;
import amine.elh.ecommerce.customer.mappers.CustomerMapper;
import amine.elh.ecommerce.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public CustomerResponse findById(String id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format("No customer found with id '%s'", id)));
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Customer with id %s not found", request.id())
                        ));
        mergeCustomer(customer,request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if (request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }

    public boolean existsById(String id) {
        return repository.findById(id)
                .isPresent();
    }
}

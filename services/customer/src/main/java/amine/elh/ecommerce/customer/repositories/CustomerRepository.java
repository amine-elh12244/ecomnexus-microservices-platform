package amine.elh.ecommerce.customer.repositories;


import amine.elh.ecommerce.customer.documents.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}

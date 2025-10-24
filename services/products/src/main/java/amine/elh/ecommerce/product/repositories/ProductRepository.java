package amine.elh.ecommerce.product.repositories;


import amine.elh.ecommerce.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}

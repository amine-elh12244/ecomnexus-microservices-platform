package amine.elh.ecommerce.product.services;


import amine.elh.ecommerce.product.dtos.ProductPurchaseRequest;
import amine.elh.ecommerce.product.dtos.ProductPurchaseResponse;
import amine.elh.ecommerce.product.dtos.ProductRequest;
import amine.elh.ecommerce.product.dtos.ProductResponse;
import amine.elh.ecommerce.product.exceptions.ProductPuchaseException;
import amine.elh.ecommerce.product.mappers.ProductMapper;
import amine.elh.ecommerce.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }


    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPuchaseException("One or more products does not exist");
        }

        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i=0 ; i<storedProducts.size() ; i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPuchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchasedResponse(product ,productRequest.quantity()));
        }

        return purchasedProducts;


    }


    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID :" + productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}

package amine.elh.ecommerce.product.controllers;


import amine.elh.ecommerce.product.dtos.ProductPurchaseRequest;
import amine.elh.ecommerce.product.dtos.ProductPurchaseResponse;
import amine.elh.ecommerce.product.dtos.ProductRequest;
import amine.elh.ecommerce.product.dtos.ProductResponse;
import amine.elh.ecommerce.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> request
            ){
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findbyId(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }





}

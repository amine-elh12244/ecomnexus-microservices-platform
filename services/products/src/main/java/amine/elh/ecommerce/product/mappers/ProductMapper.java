package amine.elh.ecommerce.product.mappers;


import amine.elh.ecommerce.product.dtos.ProductPurchaseResponse;
import amine.elh.ecommerce.product.dtos.ProductRequest;
import amine.elh.ecommerce.product.dtos.ProductResponse;
import amine.elh.ecommerce.product.entities.Category;
import amine.elh.ecommerce.product.entities.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .categoryDescription(product.getCategory() != null ? product.getCategory().getDescription() : null)
                .build();
    }

    public ProductPurchaseResponse toProductPurchasedResponse(Product product,  double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity

        );
    }
}

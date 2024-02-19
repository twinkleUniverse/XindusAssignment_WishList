package com.xindus1.ecommerce1.transformer;

import com.xindus1.ecommerce1.dto.requestdto.ProductRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.model.Product;

public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequestDTO productRequestDTO){
        return Product.builder()
                .productName(productRequestDTO.getProductName())
                .category(productRequestDTO.getCategory())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity())
                .build();
    }

    public static ProductResponseDTO productToResponse(Product product){
        return ProductResponseDTO.builder()
                .productName(product.getProductName())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}

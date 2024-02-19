package com.xindus1.ecommerce1.service;

import com.xindus1.ecommerce1.dto.requestdto.ProductRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.model.Product;

import java.util.List;

public interface ProductService {
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    public List<ProductResponseDTO> getAllProducts();
    public ProductResponseDTO getByProductId(int id) throws ProductNotFound;
}

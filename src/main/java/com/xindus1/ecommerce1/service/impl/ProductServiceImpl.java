package com.xindus1.ecommerce1.service.impl;

import com.xindus1.ecommerce1.dto.requestdto.ProductRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.model.Product;
import com.xindus1.ecommerce1.repository.ProductRepository;
import com.xindus1.ecommerce1.repository.UserRepository;
import com.xindus1.ecommerce1.service.ProductService;
import com.xindus1.ecommerce1.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    final ProductRepository productRepository;
    @Autowired
    final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository,UserRepository userRepository){
        this.productRepository=productRepository;
        this.userRepository=userRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO){
        Product product= ProductTransformer.productRequestToProduct(productRequestDTO);
        Product saveProduct=productRepository.save(product);
        return ProductTransformer.productToResponse(saveProduct);
    }

    public List<ProductResponseDTO> getAllProducts(){
        List<Product>products=productRepository.findAll();
        List<ProductResponseDTO>list=new ArrayList<>();
        for(Product product:products){
            list.add(ProductTransformer.productToResponse(product));
        }
        return list;
    }

    public ProductResponseDTO getByProductId(int id) throws ProductNotFound {
        Product product=productRepository.findById(id).get();
        if(product==null) throw new ProductNotFound("Invalid product id");
        return ProductTransformer.productToResponse(product);
    }
}

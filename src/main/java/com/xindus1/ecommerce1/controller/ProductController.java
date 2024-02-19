package com.xindus1.ecommerce1.controller;

import com.xindus1.ecommerce1.dto.requestdto.ProductRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Product")
public class ProductController {

    @Autowired
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/newProduct")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity getAllProduct() {
        List<ProductResponseDTO> ans = productService.getAllProducts();
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity get(@RequestParam("id") int id) throws ProductNotFound {
        try {
            ProductResponseDTO ans = productService.getByProductId(id);
            return new ResponseEntity(ans, HttpStatus.OK);
        } catch (ProductNotFound p) {
            return new ResponseEntity(p.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}

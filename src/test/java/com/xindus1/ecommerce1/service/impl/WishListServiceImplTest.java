package com.xindus1.ecommerce1.service.impl;

import com.xindus1.ecommerce1.dto.requestdto.WishListRequestDTO;

import static org.mockito.ArgumentMatchers.any;

import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.model.Product;
import com.xindus1.ecommerce1.model.User;
import com.xindus1.ecommerce1.model.WishList;
import com.xindus1.ecommerce1.repository.ProductRepository;
import com.xindus1.ecommerce1.repository.UserRepository;
import com.xindus1.ecommerce1.repository.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WishListServiceImplTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WishListRepository wishListRepository;

    @InjectMocks
    private WishListServiceImpl wishListService;

    @Test
    void addToWishList() {

        WishListRequestDTO requestDTO = new WishListRequestDTO();
        User user = new User();
        Product product = new Product();
        WishList wishList = new WishList();
        wishList.setUser(user);
        Set<Product> productList = new HashSet<>();
        productList.add(product);
        wishList.setProductlist(productList);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(wishListRepository.save(any())).thenReturn(wishList);

        // Act
        String result = wishListService.addToWishList(requestDTO);

        // Assert
        assertEquals("Product added in wishlist", result);

    }

    @Test
    void getAllProductOfList() {
        User user = new User();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        List<ProductResponseDTO> ans = wishListService.getAllProductOfList(1);
        assertEquals(new ArrayList<>(), ans);
    }

    @Test
    void deleteItem() {
        User user = new User();
        Product product = new Product();
        product.setId(1);
        product.setProductName("T-shirt");
        WishList wishList = new WishList();

        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        wishList.setProductlist(productSet);
        user.setWishList(wishList);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        String str = wishListService.deleteItem(2, 1);
        assertEquals("Product removed successfully", str);
        assertFalse(wishList.getProductlist().contains(product));
    }

    @Test
    void deleteItemIfNotPresent() {
        User user = new User();
        Product product = new Product();

        WishList wishList = new WishList();

        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        wishList.setProductlist(productSet);
        user.setWishList(wishList);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        String str = wishListService.deleteItem(2, 1);
        assertEquals("Product is not present in wishlist", str);

    }
}
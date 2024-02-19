package com.xindus1.ecommerce1.controller;

import com.xindus1.ecommerce1.dto.requestdto.WishListRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.exception.UserNotFoundException;
import com.xindus1.ecommerce1.service.impl.WishListServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishListControllerTest {


    @InjectMocks
    private WishListController wishListController;

    @Mock
    private WishListServiceImpl wishListService;

    //Positive TestCases
    @Test
    void addProduct() {

        WishListRequestDTO requestDTO = new WishListRequestDTO(1, 2);
        when(wishListService.addToWishList(requestDTO)).thenReturn("Product added in wishlist");

        ResponseEntity<?> response = wishListController.addProduct(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void GetUserWishlist() {
        int userId = 1;
        List<ProductResponseDTO> list = new ArrayList<>();
        when(wishListService.getAllProductOfList(userId)).thenReturn(list);
        ResponseEntity<?> response = wishListController.getUserWishlist(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    void deleteWishlistItem() {
        int userId = 1;
        int itemId = 1;
        when(wishListService.deleteItem(userId, itemId)).thenReturn("Product removed successfully");
        ResponseEntity<?> response = wishListController.deleteWishlistItem(userId, itemId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //Negative TestCases

    @Test
    void ifUserNotFoundAddProduct() throws UserNotFoundException, ProductNotFound {

        WishListRequestDTO requestDTO = new WishListRequestDTO();
        when(wishListService.addToWishList(requestDTO)).thenThrow(new UserNotFoundException("Invalid user id"));
        ResponseEntity<?> response = wishListController.addProduct(requestDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void ifProductNotFoundAddProduct() throws ProductNotFound {

        WishListRequestDTO requestDTO = new WishListRequestDTO(1, 3);
        when(wishListService.addToWishList(requestDTO)).thenThrow(new UserNotFoundException("Invalid user id"));
        ResponseEntity<?> response = wishListController.addProduct(requestDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void ifUserNotFoundGetUserWishlist() throws UserNotFoundException {
        int userId = 1;
        when(wishListService.getAllProductOfList(userId)).thenThrow(new UserNotFoundException("Invalid user id"));
        ResponseEntity<?> response = wishListController.getUserWishlist(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void ifExceptionDeleteWishlistItem() throws UserNotFoundException, ProductNotFound {
        int userId = 1;
        int itemId = 1;
        when(wishListService.deleteItem(userId, itemId)).thenThrow(new ProductNotFound("Invalid product id"));
        ResponseEntity<?> response = wishListController.deleteWishlistItem(userId, itemId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
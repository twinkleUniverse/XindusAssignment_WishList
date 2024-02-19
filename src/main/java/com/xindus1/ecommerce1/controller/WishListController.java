package com.xindus1.ecommerce1.controller;

import com.xindus1.ecommerce1.dto.requestdto.WishListRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.exception.UserNotFoundException;
import com.xindus1.ecommerce1.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("WishList")
public class WishListController {
    @Autowired
    final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    //Adding new Product to wishList
    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody WishListRequestDTO wishListRequestDTO) throws UserNotFoundException, ProductNotFound {
        try {
            String ans = wishListService.addToWishList(wishListRequestDTO);
            return new ResponseEntity<>(ans, HttpStatus.OK);
        } catch (UserNotFoundException u) {
            return new ResponseEntity(u.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotFound p) {
            return new ResponseEntity<>(p.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Get user wishlist
    @GetMapping("/get_User_Wishlist")
    public ResponseEntity getUserWishlist(@RequestParam("userId") int id) throws UserNotFoundException {
        try {
            List<ProductResponseDTO> ans = wishListService.getAllProductOfList(id);
            return new ResponseEntity<>(ans, HttpStatus.OK);
        } catch (UserNotFoundException u) {
            return new ResponseEntity<>(u.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Delete product of item_id from user of user_id wishlist
    @DeleteMapping("/deleteWishlistProduct")
    public ResponseEntity deleteWishlistItem(@RequestParam("userId") int user_id, @RequestParam("itemId") int item_id)
            throws ProductNotFound, UserNotFoundException {
        try {
            String str = wishListService.deleteItem(user_id, item_id);
            return new ResponseEntity<>(str, HttpStatus.OK);
        } catch (UserNotFoundException u) {
            return new ResponseEntity<>(u.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ProductNotFound p) {
            return new ResponseEntity<>(p.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

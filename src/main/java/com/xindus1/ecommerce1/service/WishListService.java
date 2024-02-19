package com.xindus1.ecommerce1.service;

import com.xindus1.ecommerce1.dto.requestdto.WishListRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.exception.UserNotFoundException;

import java.util.List;

public interface WishListService {
    public List<ProductResponseDTO> getAllProductOfList(int user_id) throws UserNotFoundException;
    public String addToWishList(WishListRequestDTO wishListRequestDTO)throws UserNotFoundException,
            ProductNotFound;

    public String deleteItem(int user_id,int product_id) throws UserNotFoundException,ProductNotFound;
    }

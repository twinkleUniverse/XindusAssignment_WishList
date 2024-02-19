package com.xindus1.ecommerce1.service.impl;

import com.xindus1.ecommerce1.dto.requestdto.WishListRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.ProductResponseDTO;
import com.xindus1.ecommerce1.exception.ProductNotFound;
import com.xindus1.ecommerce1.exception.UserNotFoundException;
import com.xindus1.ecommerce1.model.Product;
import com.xindus1.ecommerce1.model.User;
import com.xindus1.ecommerce1.model.WishList;
import com.xindus1.ecommerce1.repository.ProductRepository;
import com.xindus1.ecommerce1.repository.UserRepository;
import com.xindus1.ecommerce1.repository.WishListRepository;
import com.xindus1.ecommerce1.service.WishListService;
import com.xindus1.ecommerce1.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    final ProductRepository productRepository;
    @Autowired
    final UserRepository userRepository;
    @Autowired
    final WishListRepository wishListRepository;

    public WishListServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                               WishListRepository wishListRepository) {
        this.productRepository = productRepository;
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    public String addToWishList(WishListRequestDTO wishListRequestDTO) throws UserNotFoundException,
            ProductNotFound {

        if (!userRepository.findById(wishListRequestDTO.getUserId()).isPresent())
            throw new UserNotFoundException("Invalid user id");
        if (!productRepository.findById(wishListRequestDTO.getProductId()).isPresent())
            throw new ProductNotFound("Invalid product id");

        User user = userRepository.findById(wishListRequestDTO.getUserId()).get();
        Product product = productRepository.findById(wishListRequestDTO.getProductId()).get();
        WishList wishList;
        if (user.getWishList() == null) {
            wishList = new WishList();
            wishList.setUser(user);
        } else {
            wishList = user.getWishList();

            Iterator<Product> iterator = wishList.getProductlist().iterator();
            //boolean found = false;
            while (iterator.hasNext()) {
                Product p = iterator.next();
                if (p.getId() == wishListRequestDTO.getProductId()) {
                    return "Product already exist";
                }
            }
        }
        Set<Product> productSet = wishList.getProductlist();
        productSet.add(product);
        wishList.setProductlist(productSet);

        WishList saved = wishListRepository.save(wishList);


        return "Product added in wishlist";
    }

    public List<ProductResponseDTO> getAllProductOfList(int user_id) throws UserNotFoundException {
        if (!userRepository.findById(user_id).isPresent()) throw new UserNotFoundException("Invalid user id");
        User user = userRepository.findById(user_id).get();
        if (user.getWishList() == null) return new ArrayList<>();
        WishList wishList = user.getWishList();

        List<ProductResponseDTO> ans = new ArrayList<>();
        Set<Product> productList = wishList.getProductlist();

        for (Product product1 : productList) {
            ans.add(ProductTransformer.productToResponse(product1));
        }
        return ans;
    }


    public String deleteItem(int user_id, int product_id) throws UserNotFoundException, ProductNotFound {

        if (!userRepository.findById(user_id).isPresent())
            throw new UserNotFoundException("Invalid user id");
        if (!productRepository.findById(product_id).isPresent())
            throw new ProductNotFound("Invalid product id");
        User user = userRepository.findById(user_id).get();
        if (user.getWishList() == null) return "WishList is empty";

        WishList wishList = user.getWishList();
        Set<Product> productList = wishList.getProductlist();
        Iterator<Product> iterator = productList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId() == product_id) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (found) {
            user.setWishList(wishList);
            wishListRepository.save(wishList);
            return "Product removed successfully";
        } else {
            return "Product is not present in wishlist";
        }
    }
}

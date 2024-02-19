package com.xindus1.ecommerce1.repository;

import com.xindus1.ecommerce1.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {

    List<WishList> findAllByUserId(int id);
}

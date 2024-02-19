package com.xindus1.ecommerce1.repository;

import com.xindus1.ecommerce1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
     User findByUsername(String name);
     Optional<User> findByEmail(String email);
}

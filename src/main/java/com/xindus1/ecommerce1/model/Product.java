package com.xindus1.ecommerce1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name="product")
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Id
    int id;
    @Column(name="product_name")
    String productName;
    @Column(name="description")
    String description;
    @Column(name="price",nullable = false)
    BigDecimal price;
    @Column(name="category")
    String category;
    @Column(name="quantity")
    int quantity;

    @JsonIgnore
    @ManyToMany(mappedBy = "productlist",cascade = CascadeType.ALL)
    Set<WishList> wishListSet=new HashSet<>();
}

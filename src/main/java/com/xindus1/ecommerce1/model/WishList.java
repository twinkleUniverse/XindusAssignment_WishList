package com.xindus1.ecommerce1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int wishlistId;

    @JsonIgnore
    @JoinColumn(nullable = false)
    @OneToOne
    User user;

    @JsonIgnore
    @JoinTable(name = "wishlist_product", joinColumns = @JoinColumn(name = "wishlist_wishlist_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    @ManyToMany
    Set<Product> productlist = new HashSet<>();
}

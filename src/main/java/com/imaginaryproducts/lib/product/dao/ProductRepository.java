package com.imaginaryproducts.lib.product.dao;

import com.imaginaryproducts.lib.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(UUID id);
    void deleteById(UUID id);
}

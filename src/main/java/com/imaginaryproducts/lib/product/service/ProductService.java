package com.imaginaryproducts.lib.product.service;

import com.imaginaryproducts.lib.product.dto.ProductResponse;
import com.imaginaryproducts.lib.product.dto.PublicProductDTO;
import com.imaginaryproducts.lib.product.dto.PublicProductResponse;
import com.imaginaryproducts.lib.product.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<PublicProductDTO> findAllPublic();
    List<Product> findAll();
    Product findByUUID(UUID id);
    PublicProductResponse getPublicProductResponse(int currentPage, int resultsPerPage);
    ProductResponse getProductResponse(int currentPage, int resultsPerPage);
    Product save(Product product);
    Product updateProduct(Product product, UUID id);
    void delete(UUID id);
}

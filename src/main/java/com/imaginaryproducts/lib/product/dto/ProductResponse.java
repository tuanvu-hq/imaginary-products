package com.imaginaryproducts.lib.product.dto;

import com.imaginaryproducts.lib.product.entity.Product;

import java.util.List;

public record ProductResponse(
        List<Product> data,
        Integer currentPage,
        Integer lastPage,
        Integer resultsPerPage,
        Integer totalResults
) {
}

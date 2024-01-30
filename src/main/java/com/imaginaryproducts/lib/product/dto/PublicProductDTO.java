package com.imaginaryproducts.lib.product.dto;

public record PublicProductDTO(
        String name,
        String description,
        Double price,
        Long quantity
) {
}
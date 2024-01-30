package com.imaginaryproducts.lib.product.dto;

import com.imaginaryproducts.lib.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static PublicProductDTO getPublicProductDTO(Product product) {
        return new PublicProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}

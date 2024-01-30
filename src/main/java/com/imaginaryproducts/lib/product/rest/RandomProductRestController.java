package com.imaginaryproducts.lib.product.rest;

import com.imaginaryproducts.lib.product.entity.Product;
import com.imaginaryproducts.lib.product.entity.ProductRandomizer;
import com.imaginaryproducts.lib.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products/random")
public class RandomProductRestController {
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(RandomProductRestController.class);

    /**
     * [REST API: GET] Create a {@link Product}. The product will have randomized properties with {@link ProductRandomizer}.
     *
     * @param amount An optional parameter with a default of 1. Defines the amount of created {@link Product}.
     * @return - Returns a newly created {@link Product}.
     * @see ProductRandomizer
     */
    @GetMapping("/save")
    public List<Product> saveProduct(@RequestParam(value = "amount", defaultValue = "1", required = false) Long amount) {
        List<Product> list = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Product product = ProductRandomizer.build();

            list.add(product);

            productService.save(product);
        }

        logger.info("[APP - REST API: POST] Saving a product.");

        return list;
    }
}

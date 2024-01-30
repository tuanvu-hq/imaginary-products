package com.imaginaryproducts.lib.product.rest;

import com.imaginaryproducts.lib.product.entity.Product;
import com.imaginaryproducts.lib.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable UUID id) {
        return productService.findByUUID(id);
    }

    @PostMapping()
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable UUID id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {
       productService.delete(id);

        return "Product deleted.";
    }
}

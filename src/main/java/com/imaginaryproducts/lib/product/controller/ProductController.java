package com.imaginaryproducts.lib.product.controller;

import com.imaginaryproducts.lib.product.entity.Product;
import com.imaginaryproducts.lib.product.entity.ProductRandomizer;
import com.imaginaryproducts.lib.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * [Controller: GET /create] Create a {@link Product}. The product will have randomized properties with {@link ProductRandomizer}.
     *
     * @param amount An optional parameter with a default of 1. Defines the amount of created {@link Product}.
     * @return - Redirects to '/dashboard'.
     */
    @GetMapping("/create")
    public String pageCreateProduct(@RequestParam(value = "amount", defaultValue = "1", required = false) Long amount) {
        for (int i = 0; i < amount; i++) {
            Product product = ProductRandomizer.build();

            logger.info("[APP - Product Controller] Created a product " + product.getId());

            productService.save(product);
        }

        logger.info("[APP - Product Controller] Finished creating products.");
        logger.info("[APP - Product Controller] Redirecting to '/dashboard'.");

        return "redirect:/dashboard";
    }

    /**
     * [Controller: GET /update] Navigates to a template.
     *
     * @param id Represents and ID of {@link Product}.
     * @return - Returns a template.
     */
    @GetMapping("/update")
    public String pageUpdateProduct(@RequestParam("id") UUID id, Model model) {
        Product product = productService.findByUUID(id);

        model.addAttribute("product", product);

        logger.info("[APP - Product Controller: GET /update] Navigates to '/pages/products/update'.");

        return "pages/products/update";
    }

    /**
     * [Controller: GET /delete] Navigates to a template.
     *
     * @param id Represents and ID of {@link Product}.
     * @return - Returns a template.
     */
    @GetMapping("/delete")
    public String pageDeleteProduct(@RequestParam("id") UUID id, Model model) {
        Product product = productService.findByUUID(id);

        model.addAttribute("product", product);

        logger.info("[APP - Product Controller: GET /delete] Navigates to '/pages/products/delete'.");

        return "pages/products/delete";
    }

    /**
     * [Controller: POST /save-changes] Updates the {@link Product}.
     *
     * @param product Represents the {@link Product}.
     * @return - Redirects to '/dashboard'.
     */
    @PostMapping("/save-changes")
    public String saveChanges(@ModelAttribute("product") Product product) {
        productService.save(product);

        logger.info("[APP - Product Controller: POST /save-changes] Saved product changes.");
        logger.info("[APP - Product Controller: POST /save-changes] Redirecting to '/dashboard'.");

        return "redirect:/dashboard";
    }

    /**
     * [Controller: GET /remove] Deletes the {@link Product}.
     *
     * @param id Represents the ID of {@link Product}.
     * @return - Redirects to '/dashboard'.
     */
    @GetMapping("/remove")
    public String deleteProduct(@RequestParam("id") UUID id) {
        productService.delete(id);

        logger.info("[APP - Product Controller: POST /remove] Product deleted.");
        logger.info("[APP - Product Controller: POST /remove] Redirecting to '/dashboard'.");

        return "redirect:/dashboard";
    }
}

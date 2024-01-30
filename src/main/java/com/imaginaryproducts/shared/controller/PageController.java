package com.imaginaryproducts.shared.controller;

import com.imaginaryproducts.lib.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class PageController {
    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String pageHome(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "resultsPerPage", defaultValue = "10", required = false) int resultsPerPage,
            Model model
    ) {
        model.addAttribute("productResponse", productService.getPublicProductResponse(currentPage, resultsPerPage));

        return "pages/home";
    }

    @GetMapping("/login")
    public String pageLogin() {
        return "pages/login";
    }

    @GetMapping("/dashboard")
    public String pageDashboard(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
            @RequestParam(value = "resultsPerPage", defaultValue = "10", required = false) int resultsPerPage,
            Model model
    ) {
        model.addAttribute("productResponse", productService.getProductResponse(currentPage, resultsPerPage));

        return "pages/dashboard";
    }
}

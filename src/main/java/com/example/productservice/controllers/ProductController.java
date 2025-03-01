package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}

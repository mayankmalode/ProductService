package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    public void deleteProduct(Long productId){}

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("name") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
    @PutMapping("/{id}")
    public  Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }
}

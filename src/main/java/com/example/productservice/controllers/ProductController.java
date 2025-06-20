package com.example.productservice.controllers;

import com.example.productservice.commons.AuthUtil;
import com.example.productservice.dtos.UserDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthUtil authUtil;

    public ProductController(@Qualifier("fakeStoreProductService")
                             ProductService productService,
                             AuthUtil authUtil) {
        this.productService = productService;
        this.authUtil = authUtil;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
        return response;
    }

    //OLD METHOD
//    @GetMapping()
//    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber,@RequestParam("pageSize") int pageSize){
//
//        return productService.getAllProducts(pageNumber, pageSize);
//    }


    //Validating token before
    @GetMapping("/{tokenValue}")
    public ResponseEntity<Page<Product>> getAllProducts(@PathVariable String tokenValue,
                                        @RequestParam("pageNumber") int pageNumber,
                                        @RequestParam("pageSize") int pageSize){

        ResponseEntity<Page<Product>> responseEntity = null;

        //make a call to UserService to validate the token.
        UserDto userDto = authUtil.validateToken(tokenValue);

        if(userDto == null)
        {
            //Token is invalid
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }

        responseEntity = new ResponseEntity<>(productService.getAllProducts(pageNumber, pageSize), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
    }
    @PutMapping("/{id}")
    public  Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }
}

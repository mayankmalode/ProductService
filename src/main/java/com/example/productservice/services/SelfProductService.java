package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        //Make a call to DB to fetch a product with a given id
        Optional<Product> optionalProduct = productRepository.findById(productId);

        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Patch call
    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with id: " + id + " doesn't exists.");
        }

        Product productInDB = optionalProduct.get();
        if(product.getName() != null){
            productInDB.setName(product.getName());
        }
        if(product.getTitle() != null){
            productInDB.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDB.setPrice(product.getPrice());
        }

        return productRepository.save(productInDB);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with id: " + id + "does not exists. Please verify the id");
        }

        Product productInDB = optionalProduct.get();
        productInDB.setName(product.getName());
        productInDB.setTitle(product.getTitle());
        productInDB.setPrice(product.getPrice());

        return productRepository.save(productInDB);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();

        if(category != null) {
            category = categoryRepository.save(category);
            product.setCategory(category);
        }

        return productRepository.save(product);
    }
}

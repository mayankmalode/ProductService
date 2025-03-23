package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product Repo should contain all the methods (CRUD) related to Product Model

    Optional<Product> findById(Long id);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    void deleteById(Long id);
}


/*
1. Repository should be an interface.
2. Repository should extend JPARepository
 */
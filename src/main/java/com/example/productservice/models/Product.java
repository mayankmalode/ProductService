package com.example.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String title;
    private double price;
    private Category category;
}

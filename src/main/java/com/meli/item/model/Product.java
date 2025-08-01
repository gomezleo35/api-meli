package com.meli.item.model;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;   
}
package com.meli.item.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.item.model.Product;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
    private List<Product> products;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/products.json");
        products = Arrays.asList(mapper.readValue(inputStream, Product[].class));
    }
    
    public List<Product> getAll() {
        return products;
    }

    public Product getById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}

package com.meli.item.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.meli.item.model.Product;
import com.meli.item.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final Product product1 = new Product("123", "Zapatillas", "Zapatillas con suela de goma.", 20000);
    private final Product product2 = new Product("124", "Nintendo", "Consola de video juegos.", 20012);
    
    @Test
    void testGetAll() throws Exception {
        when(productService.getAll()).thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/api/products"))
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].name").value("Zapatillas"))
        .andExpect(jsonPath("$[1].name").value("Nintendo"));
    }

    @Test
    void testGetById_found() throws Exception {
        when(productService.getById("123")).thenReturn(product1);

        mockMvc.perform(get("/api/products/123"))
        .andExpect(jsonPath("$.id").value("123"))
        .andExpect(jsonPath("$.name").value("Zapatillas"))
        .andExpect(jsonPath("$.description").value("Zapatillas con suela de goma."))
        .andExpect(jsonPath("$.price").value(20000));
    }

    @Test
    void testGetById_notFound() throws Exception {
        when(productService.getById("345")).thenReturn(null);

        mockMvc.perform(get("/api/products/123"))
        .andExpect(status().isNotFound());
    }

    @Test
    void testSearch_found() throws Exception {
        when(productService.search("de")).thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/api/products/search?query=de"))
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].name").value("Zapatillas"))
        .andExpect(jsonPath("$[1].name").value("Nintendo"));
    }

    @Test
    void testSearch_noResult() throws Exception {
        List<Product> list = new ArrayList<>();
        when(productService.search("rojo")).thenReturn(list);

        mockMvc.perform(get("/api/products/search?query=de"))
        .andExpect(status().isNotFound());
    }
}

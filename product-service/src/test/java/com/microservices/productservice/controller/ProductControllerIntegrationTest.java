package com.microservices.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productservice.AbstractIntegrationTest;
import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ProductControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());

        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void shouldReturnAllProducts() throws Exception {

        Product product1 = productRepository.save(Product.builder()
                .name("Huawei").price(BigDecimal.valueOf(1200)).description("Huawei Phone").build());
        Product product2 = productRepository.save(Product.builder()
                .name("Samsung").price(BigDecimal.valueOf(1300)).description("Samsung Phone").build());

        productRepository.save(product1);
        productRepository.save(product2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                // product1
                .andExpect(jsonPath("$[0].id", is(product1.getId())))
                .andExpect(jsonPath("$[0].name", is(product1.getName())))
                .andExpect(jsonPath("$[0].description", is(product1.getDescription())))
                .andExpect(jsonPath("$[0].price", is(product1.getPrice().intValue())))
                // product2
                .andExpect(jsonPath("$[1].id", is(product2.getId())))
                .andExpect(jsonPath("$[1].name", is(product2.getName())))
                .andExpect(jsonPath("$[1].description", is(product2.getDescription())))
                .andExpect(jsonPath("$[1].price", is(product2.getPrice().intValue())));
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("iPhone 13")
                .description("iPhone 13")
                .price(BigDecimal.valueOf(1200))
                .build();
    }
}

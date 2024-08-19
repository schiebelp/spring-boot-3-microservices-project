package com.microservices.inventory_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.inventory_service.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class InventoryControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

// todo
//    @Test
//    void isInStock() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/testSkuCode/10"))
//                .andExpect(status().isOk());
//    }

}

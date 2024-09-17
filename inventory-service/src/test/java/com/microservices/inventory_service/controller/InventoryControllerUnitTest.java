package com.microservices.inventory_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.inventory_service.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class InventoryControllerUnitTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void isInStock() throws Exception {
        var item = createInventoryItem();
        assertTrue(inventoryRepository.existsById(item.getId()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory?skuCode="+item.getSkuCode()+"&quantity="+item.getQuantity()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void outOfStock() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory?skuCode=<NON_EXISTENT>&quantity=1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

}

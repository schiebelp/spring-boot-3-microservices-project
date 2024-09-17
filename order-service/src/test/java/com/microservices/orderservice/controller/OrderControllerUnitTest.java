package com.microservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.orderservice.AbstractTest;
import com.microservices.orderservice.client.InventoryClient;
import com.microservices.orderservice.dto.OrderLineItemDTO;
import com.microservices.orderservice.dto.OrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class OrderControllerUnitTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InventoryClient inventoryClient;

    @Test
    void shouldCreateOrder() throws Exception {
        OrderRequest orderRequest = getOrderRequest();

        when(inventoryClient.isInStock(
                orderRequest.getItems().get(0).getSkuCode(),
                orderRequest.getItems().get(0).getQuantity())
        ).thenReturn(true);

        String productRequestString = objectMapper.writeValueAsString(orderRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());

        assertEquals(1, orderRepository.findAll().size());
    }

    private OrderRequest getOrderRequest() {
        return OrderRequest.builder()
                .items(
                        List.of(OrderLineItemDTO.builder()
                                .skuCode("testSkuCode")
                                .quantity(20)
                                .price(BigDecimal.valueOf(0.99))
                                .build())
                )
                .build();
    }
}

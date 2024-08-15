package com.microservices.orderservice.controller;

import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.dto.OrderResponse;
import com.microservices.orderservice.mapper.OrderMapper;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/orders", produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderMapper mapper;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest request) {
        log.info("Create Order request: {}", request);

        orderService.placeOrder(
                mapper.toModel(request, UUID.randomUUID().toString())
        );

        return "Order created successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(mapper::toDto)
                .toList();
    }


}

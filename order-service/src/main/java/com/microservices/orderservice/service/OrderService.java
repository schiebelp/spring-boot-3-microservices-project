package com.microservices.orderservice.service;

import com.microservices.orderservice.client.InventoryClient;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItem;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(Order order) {
        log.debug("Placing order: {}", order);

        List<OrderLineItem> outOfStockItems = retrieveNonExistentItems(order);

        if(!CollectionUtils.isEmpty(outOfStockItems)) {
            String outOfStockMessage = outOfStockItems.stream()
                    .map(item -> String.format("Product %s is not in stock. Requested: %d", item.getSkuCode(), item.getQuantity()))
                    .collect(Collectors.joining(", "));
            log.debug(outOfStockMessage);
            throw new IllegalArgumentException(outOfStockMessage);
        }

        orderRepository.save(order);
        log.debug("Order {} is saved", order.getId());
    }

    private List<OrderLineItem> retrieveNonExistentItems(Order order) {
        return order.getOrderLineItems().stream()
                .filter(item -> {
                    log.debug("Checking inventory for item {}:{}", item.getSkuCode(), item.getQuantity());
                    return !inventoryClient.isInStock(item.getSkuCode(), item.getQuantity());
                })
                .toList();
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

}

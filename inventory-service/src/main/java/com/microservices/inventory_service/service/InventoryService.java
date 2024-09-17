package com.microservices.inventory_service.service;

import com.microservices.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository repository;

    /**
     * Check if inventory is available
     *
     * @param skuCode Stock Keeping Unit code
     * @param quantity Quantity
     * @return true if inventory is available
     */
    public boolean isInStock(String skuCode, Integer quantity) {
        log.debug("Checking inventory for skuCode: {} and quantity: {}", skuCode, quantity);
        return repository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}

package com.microservices.productservice.data;

import com.microservices.productservice.model.Product;
import com.microservices.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Initialize default products for production environment.
 *
 * If you know how to use flyway with mongodb, let me know (i had problem with dependencies)
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test") // Skip for testing environment
public class ProductDataInitializer {

    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        if (productRepository.count() == 0) { // Only insert if the DB is empty
            productRepository.saveAll(DEFAULT_PRODUCT_LIST);
            log.debug("Default products initialized.");
        }
    }

    private static final List<Product> DEFAULT_PRODUCT_LIST = new ArrayList<>();
    static {
        DEFAULT_PRODUCT_LIST.add(new Product("1001", "IPhone 15", "todo", BigDecimal.valueOf(34.99)));
        DEFAULT_PRODUCT_LIST.add(new Product("1002", "Pixel 8", "", BigDecimal.valueOf(8.50)));
        DEFAULT_PRODUCT_LIST.add(new Product("1003", "Galaxy 24", "", BigDecimal.valueOf(17.80)));
        DEFAULT_PRODUCT_LIST.add(new Product("1004", "OnePlus 12", "", BigDecimal.valueOf(28.75)));
    }

}
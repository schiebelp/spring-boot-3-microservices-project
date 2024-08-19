package com.microservices.inventory_service;

import com.microservices.inventory_service.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public abstract class AbstractIntegrationTest {

    @Autowired
    protected InventoryRepository inventoryRepository;

    /**
     * Test container PostgreSQ Database
     *
     * Static final means that the instance is shared between all test methods
     * (see: https://java.testcontainers.org/test_framework_integration/junit_5/)
     */
    @Container
    static final PostgreSQLContainer<?> postgre = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.datasource.url", postgre::getJdbcUrl);
        dymDynamicPropertyRegistry.add("spring.datasource.password", postgre::getPassword);
        dymDynamicPropertyRegistry.add("spring.datasource.username", postgre::getUsername);
    }

    @AfterEach
    void tearDown() { //clean up
        inventoryRepository.deleteAll();
    }
}

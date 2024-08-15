package com.microservices.orderservice;

import com.microservices.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
public abstract class AbstractIntegrationTest {

    @Autowired
    protected OrderRepository orderRepository;

    @Container
    static PostgreSQLContainer postgreDBContainer = new PostgreSQLContainer("postgres:16-alpine");

    static {
        postgreDBContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.datasource.url", postgreDBContainer::getJdbcUrl);
        dymDynamicPropertyRegistry.add("spring.datasource.password", postgreDBContainer::getPassword);
        dymDynamicPropertyRegistry.add("spring.datasource.username", postgreDBContainer::getUsername);
    }

    @AfterEach
    void tearDown() { //clean up
        orderRepository.deleteAll();
    }

}

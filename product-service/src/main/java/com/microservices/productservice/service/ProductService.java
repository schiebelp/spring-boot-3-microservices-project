package com.microservices.productservice.service;

import com.microservices.productservice.model.Product;
import com.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public void createProduct(Product product) {

        repository.save(product);
        log.debug("Product {} is saved", product.getId());

    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

}

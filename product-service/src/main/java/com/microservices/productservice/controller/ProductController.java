package com.microservices.productservice.controller;

import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.mapper.ProductMapper;
import com.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/products", produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductMapper mapper;
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        log.debug("Create Product request: {}", request);

        productService.createProduct(
                mapper.toModel(request));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts().stream()
                                                 .map(mapper::toDTO)
                                                 .toList();
    }

}

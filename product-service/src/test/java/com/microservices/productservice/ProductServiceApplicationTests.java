package com.microservices.productservice;

import com.microservices.productservice.controller.ProductController;
import com.microservices.productservice.mapper.ProductMapper;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductServiceApplicationTests extends AbstractTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ProductController productController;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductRepository productRepository;


	/**
	 * Context loads and also all beans
	 *
	 * https://www.jvt.me/posts/2021/06/25/spring-context-test/
	 */
	@Test
	void contextLoads() {

		// check if all beans are injected
		assertThat(productController).isNotNull();
		assertThat(productService).isNotNull();
		assertThat(productMapper).isNotNull();
		assertThat(productRepository).isNotNull();

		// check if context is injected
		assertThat(context).isNotNull();
	}

}

package com.microservices.orderservice;

import com.microservices.orderservice.controller.OrderController;
import com.microservices.orderservice.mapper.OrderMapper;
import com.microservices.orderservice.repository.OrderRepository;
import com.microservices.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OrderServiceApplicationTests extends AbstractTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private OrderController orderController;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderRepository orderRepository;


	/**
	 * Explicit context loads
	 *
	 * https://www.jvt.me/posts/2021/06/25/spring-context-test/
	 */
	@Test
	void contextLoads() {

		// check if all beans are injected
		assertThat(orderController).isNotNull();
		assertThat(orderService).isNotNull();
		assertThat(orderMapper).isNotNull();
		assertThat(orderRepository).isNotNull();


		// check if context is injected
		assertThat(context).isNotNull();

	}

}

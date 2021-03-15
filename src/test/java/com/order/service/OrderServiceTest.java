package com.order.service;

import com.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    void queryOne() {
        Order order = orderService.queryOne("402828725d401f46015d4037cb830000");
        System.out.println(order.getOrderId());
    }
}
package com.order.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RouteSendTest {
    @Autowired
    private RouteSend routeSend;

    @Test
    void send() {
        routeSend.send("路由消息测试1");
    }
}
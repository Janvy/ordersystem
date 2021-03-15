package com.order.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TopicSendTest {
    @Autowired
    private TopicSend topicSend;
    @Test
    void send() {
        topicSend.send("topic测试");
    }
}
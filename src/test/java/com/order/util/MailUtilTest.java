package com.order.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailUtilTest {
    @Autowired
    private MailUtil mailUtil;

    @Test
    void send() {
        mailUtil.send("测试","我就测试一下");
    }
}
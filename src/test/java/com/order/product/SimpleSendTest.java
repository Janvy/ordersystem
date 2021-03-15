package com.order.product;

import com.order.consumer.SimpleConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SimpleSendTest {
    @Autowired
    private SimpleSend simpleSend;
    @Autowired
    private SimpleConsumer simpleConsumer;

    @Test
    public void send() {
        simpleSend.send("测试一个");
    }

}
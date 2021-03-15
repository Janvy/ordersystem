package com.order.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FanoutSendTest {
    @Autowired
    private FanoutSend fanoutSend;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void send() {
        fanoutSend.send("测试发送到交换机7");
    }

    @Test
    void save(){
        RedisObject redisObject = new RedisObject();
        redisObject.setName("刘伟杰");
        redisObject.setSex("男");
        redisObject.setAge(18);
        redisTemplate.opsForValue().set("key1",redisObject);

//        redisTemplate.opsForValue().get("key1");
    }


}
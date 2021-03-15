package com.order.product;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/26 11:04
 * @Version: 1.0
 * @Description:
 */
@Component
public class TopicSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String text) {
        Message message = MessageBuilder.withBody(text.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId("123456")
                .build();
        rabbitTemplate.convertAndSend("topicExchange","order.add",message,new CorrelationData("ordersystem"));

    }
}

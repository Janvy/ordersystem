package com.order.product;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 17:52
 * @Version: 1.0
 * @Description:
 */
@Component
public class RouteSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void send(String text) {
        Message message = MessageBuilder.withBody(text.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId("1234567")
                .build();
        rabbitTemplate.convertAndSend("directExchange","directRoute",message,new CorrelationData("ordersystem"));
    }
}

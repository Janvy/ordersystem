package com.order.product;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 16:25
 * @Version: 1.0
 * @Description:
 */
@Component
public class FanoutSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * @Description 消息发送
     * @Author liuweijie
     * @Date 2021/2/24 16:28
     * @param text
     * @return void
     **/
    public void send(String text) {
        Message message = MessageBuilder.withBody(text.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId("123456")
                .build();
        rabbitTemplate.convertAndSend("fanoutExchange","workqueue",message,new CorrelationData("ordersystem"));
    }
}

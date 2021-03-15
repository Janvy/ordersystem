package com.order.product;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 13:44
 * @Version: 1.0
 * @Description:
 */
@Component
public class SimpleSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String context) {
        rabbitTemplate.convertAndSend(context);
//        System.out.println(context);
    }

}

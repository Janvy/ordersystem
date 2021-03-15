package com.order.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 13:45
 * @Version: 1.0
 * @Description:
 */
@Component
@RabbitListener(queues = "simplequeue")
public class SimpleConsumer {

    @RabbitListener(queues = "simplequeue")
    public void execute(String context, Channel channel, Message message) {
        System.out.println("consume:"+context);
    }

}

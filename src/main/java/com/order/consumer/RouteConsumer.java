package com.order.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 17:58
 * @Version: 1.0
 * @Description:
 */
@Component
public class RouteConsumer{
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = "directQueue",durable = "true"),
            exchange = @Exchange(value = "directExchange",type = ExchangeTypes.DIRECT),
            key = "directRoute")
    })
    public void receive(@Payload String context, Channel channel, Message message) {
        try {
            System.out.println("消息消费："+context);
            //设定消费一条
            channel.basicQos(1);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

        } catch (Exception e) {
            //            e.printStackTrace();

            try {
                //剔除此消息，记录到redis中
                redisTemplate.opsForValue().set("order:"+message.getMessageProperties().getMessageId(),context);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                //拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}

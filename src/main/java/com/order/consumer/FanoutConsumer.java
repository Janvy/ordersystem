package com.order.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 16:32
 * @Version: 1.0
 * @Description:
 */
@Component
public class FanoutConsumer {
    @Autowired
    private FanoutExchange fanoutExchange;
    @Autowired
    @Qualifier("fanoutqueue")
    private Queue queue;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Bean
    public Binding binding() {
        /** 将队列绑定到交换机 */
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @RabbitListener(queues = "fanoutqueue")
    public void receive(@Payload String context, Channel channel, Message message) {
        try {
            System.out.println(context);
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

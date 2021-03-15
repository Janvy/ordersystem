package com.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuweijie
 * @CreateDate: 2021/2/24 13:43
 * @Version: 1.0
 * @Description:
 */
@Configuration
@Slf4j
public class RabbitMQConfig {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback((correlationData, ack , cause) -> {
            if (ack) {
                log.info("消息发送成功");
            } else {
                log.error("消息发送失败");
            }
        });
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback((returnedMessage)->{
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                    returnedMessage.getExchange(), returnedMessage.getRoutingKey(), returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getMessage());
        });
        return rabbitTemplate;
    }

    @Bean
    public Queue simplequeue(){
        return new Queue("simplequeue",true);
    }
    @Bean
    public Queue fanoutqueue(){
        return new Queue("fanoutqueue",true);
    }

    /** 设置交换机类型  */
    @Bean
    public FanoutExchange defaultExchange() {
        /**
         * DirectExchange:按照routingkey分发到指定队列
         * TopicExchange:多关键字匹配
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         * HeadersExchange ：通过添加属性key-value匹配
         */
        return new FanoutExchange("fanoutExchange");
    }



}

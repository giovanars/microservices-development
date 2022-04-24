package br.com.kitchen.config;

import br.com.kitchen.logic.OrderLogic;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqListenerConfig {

    @Value("${rabbitmq.order.queue}")
    String orderQueue;

    @Autowired
    ConnFactory connFactory;

    @Bean
    Queue orderQueue(){
        return new Queue(orderQueue, true);
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connFactory.getConnection());
        simpleMessageListenerContainer.setQueues(orderQueue());
        simpleMessageListenerContainer.setMessageListener(new OrderLogic());
        return simpleMessageListenerContainer;
    }

}

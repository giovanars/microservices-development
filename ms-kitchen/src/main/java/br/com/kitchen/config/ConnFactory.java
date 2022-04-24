package br.com.kitchen.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConnFactory {
    @Value("${rabbitmq.hostname}")
    String hostname;

    @Value("${rabbitmq.username}")
    String username;

    @Value("${rabbitmq.passwd}")
    String passwd;

    @Value("${rabbitmq.virtualHost}")
    String virtualHost;

    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(hostname);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(passwd);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        return cachingConnectionFactory;
    }

    public ConnectionFactory getConnection(){ return connectionFactory(); }
}

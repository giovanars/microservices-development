package br.com.order.infra;

import br.com.order.configs.RabbitMqConnectionFactory;
import br.com.order.models.entities.Order;
import com.google.gson.Gson;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Value("${rabbitmq.order.exchange}")
    private String orderExchange;

    @Value("${rabbitmq.order.queue}")
    private String orderQueue;

    @Autowired
    private RabbitMqConnectionFactory rabbitMqConnectionFactory;

    @Override
    public void SendOrder(Order order){

        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitMqConnectionFactory.getConnection());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitMqConnectionFactory.getConnection());

        DirectExchange directExchange = new DirectExchange(orderExchange);
        rabbitAdmin.declareExchange(directExchange);

        Queue queue = new Queue(orderQueue);
        rabbitAdmin.declareQueue(queue);

        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(queue.getName()));
        rabbitTemplate.convertAndSend(orderExchange, queue.getName(), new Gson().toJson(order));
    }
}

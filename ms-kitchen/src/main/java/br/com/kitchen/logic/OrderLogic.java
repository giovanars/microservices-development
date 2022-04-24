package br.com.kitchen.logic;

import br.com.kitchen.config.ConnFactory;
import br.com.kitchen.model.Order;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class OrderLogic implements MessageListener {
    private static final Logger logger = LogManager.getLogger(OrderLogic.class);

    @Value("${rabbitmq.order.done.exchange}")
    private String orderDoneExchange;

    @Value("${rabbitmq.order.done.queue}")
    private String orderDoneQueue;

    @Autowired
    private ConnFactory connFactory;

    @Override
    public void onMessage(Message message) {
        String orderStr = new String(message.getBody(), StandardCharsets.UTF_8);

        Order order = new Gson().fromJson(orderStr, Order.class);
        order.setDone(false);

        processNewOrder(order);
    }

    public Order processNewOrder(Order order){

        // TODO : Como enviar o obj 'Order' p/ o front exibir?

        logger.info("New Order Info.: " + new Gson().toJson(order, Order.class));
        return order;
    }

    public void orderIsDone(Order order){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connFactory.getConnection());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connFactory.getConnection());

        DirectExchange directExchange = new DirectExchange(orderDoneExchange);
        rabbitAdmin.declareExchange(directExchange);

        Queue queue = new Queue(orderDoneQueue);
        rabbitAdmin.declareQueue(queue);

        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(queue.getName()));

        order.setDone(true);

        rabbitTemplate.convertAndSend(orderDoneExchange, queue.getName(), new Gson().toJson(order));
    }

}

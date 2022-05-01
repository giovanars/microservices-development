package br.com.kitchen.logic;

import br.com.kitchen.config.ConnFactory;
import br.com.kitchen.infra.SlackIntegrationServiceImpl;
import br.com.kitchen.model.Order;
import br.com.kitchen.model.dtos.OrderMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Value("${rabbitmq.order.exchange}")
    private String orderExchange;

    @Value("${rabbitmq.order.done.queue}")
    private String orderDoneQueue;

    @Autowired
    private ConnFactory connFactory;

    @Autowired
    private SlackIntegrationServiceImpl slackIntegrationService;


    @Override
    public void onMessage(Message message) {
        String orderStr = new String(message.getBody(), StandardCharsets.UTF_8);
        OrderMessageDto order = new Gson().fromJson(orderStr, OrderMessageDto.class);

        //TODO: Enviar notificação no slack

        try {
            slackIntegrationService.SendMessage(order.getId());
        } catch (JsonProcessingException e) {
            logger.info("Erro pra enviar mensagem pelo Slack");
        }

        processNewOrder(order);
    }

    public void processNewOrder(OrderMessageDto order){
        //TODO: Criar order no banco
        logger.info("New Order Info.: " + new Gson().toJson(order, Order.class));

    }

    public void orderIsDone(Order order){

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connFactory.getConnection());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connFactory.getConnection());
        DirectExchange directExchange = new DirectExchange(orderExchange);
        rabbitAdmin.declareExchange(directExchange);
        Queue queue = new Queue(orderDoneQueue);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(queue.getName()));

        order.setDone(true);
        rabbitTemplate.convertAndSend(orderExchange, queue.getName(), new Gson().toJson(order));
    }

}

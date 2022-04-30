package br.com.order.infra;

import br.com.order.models.entities.Order;

public interface RabbitMqService {
    void SendOrder(Order order);
}

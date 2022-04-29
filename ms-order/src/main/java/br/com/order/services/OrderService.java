package br.com.order.services;

import br.com.order.models.dtos.OrderRequestDto;
import br.com.order.models.dtos.OrderResponseDto;
import br.com.order.models.entities.Order;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAll() throws IOException;
    OrderResponseDto getById(String id) throws IOException;
    Order create(OrderRequestDto menu);
    Order update(String id, OrderRequestDto menu);
    void delete(String id);
}

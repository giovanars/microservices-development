package br.com.order.services;

import br.com.order.infra.MenuService;
import br.com.order.infra.RabbitMqService;
import br.com.order.models.dtos.OrderItemResponseDto;
import br.com.order.models.dtos.OrderRequestDto;
import br.com.order.models.dtos.OrderResponseDto;
import br.com.order.models.entities.Order;
import br.com.order.models.entities.OrderItems;
import br.com.order.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;

    private final RabbitMqService rabbitMqService;

    public OrderServiceImpl(OrderRepository orderRepository, MenuService menuService, RabbitMqService rabbitMqService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.rabbitMqService = rabbitMqService;
    }

    @Override
    public List<OrderResponseDto> getAll() throws IOException {
        List<Order> orders = orderRepository.findAll();

        List<OrderResponseDto> ordersResponse = new ArrayList<>();

        for(Order order: orders){
            List<OrderItemResponseDto> itemsResponse = new ArrayList<>();
            for (OrderItems item: order.getItems()) {
                var menuById = menuService.GetMenuById(item.getMenuIdentifier());
                itemsResponse.add(new OrderItemResponseDto(menuById));
            }

            ordersResponse.add(new OrderResponseDto(order, itemsResponse));
        }


        return ordersResponse;
    }

    @Override
    public OrderResponseDto getById(String id) throws IOException {
        Order order = orderRepository.findById(id).get();

        List<OrderItemResponseDto> itemsResponse = new ArrayList<>();
        for (OrderItems item: order.getItems()) {
            var menuById = menuService.GetMenuById(item.getMenuIdentifier());
            itemsResponse.add(new OrderItemResponseDto(menuById));
        }

        return new OrderResponseDto(order, itemsResponse);
    }

    @Override
    public Order create(OrderRequestDto orderDto) {
        Order order = orderRepository.save(new Order(orderDto));
        rabbitMqService.SendOrder(order);
        return order;
    }

    @Override
    public Order update(String id, OrderRequestDto orderDto) {
        Order order = orderRepository.findById(id).get();
        order.setItems(orderDto.getItems());
        return orderRepository.save(order);
    }

    @Override
    public void delete(String id) {
        orderRepository.delete(orderRepository.findById(id).get());
    }
}

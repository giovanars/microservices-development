package br.com.order.models.dtos;

import br.com.order.models.entities.Order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {

    private String id;
    private List<OrderItemResponseDto> items;
    private Double total;
    private LocalDateTime createdAt;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Order order, List<OrderItemResponseDto> itemsResponse) {
        this.setId(order.getId());
        this.setItems(itemsResponse);
        this.setCreatedAt(order.getCreatedAt());
        this.setTotal(itemsResponse.stream().mapToDouble(OrderItemResponseDto::getPrice).sum());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponseDto> items) {
        this.items = items;
    }


    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotal() {
        return total;
    }
}

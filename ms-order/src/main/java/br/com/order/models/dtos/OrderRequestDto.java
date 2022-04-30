package br.com.order.models.dtos;

import br.com.order.models.entities.OrderItems;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequestDto {

    private List<OrderItems> items;
    private LocalDateTime createdAt;

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

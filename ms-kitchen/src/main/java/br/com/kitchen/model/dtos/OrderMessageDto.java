package br.com.kitchen.model.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMessageDto {

    private String id;
    private List<OrderItemMessageDto> items;
    private LocalDateTime createdAt;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItemMessageDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemMessageDto> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

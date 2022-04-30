package br.com.order.models.entities;

import br.com.order.models.dtos.OrderRequestDto;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Order {

    @Id
    private String id;
    private List<OrderItems> items;
    private LocalDateTime createdAt;

    public Order(){

    }

    public Order(OrderRequestDto orderDto) {
        ObjectId id = new ObjectId();
        this.setId(id.toString());
        this.setCreatedAt(LocalDateTime.now());
        this.setItems(orderDto.getItems());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

package br.com.menu.models.entities;

import br.com.menu.models.dtos.MenuDto;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document
public class Menu {

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private LocalDate createdAt;

    public Menu(){

    }
    public Menu(MenuDto menuDto) {
        ObjectId id = new ObjectId();
        this.setId(id.toString());
        this.setName(menuDto.getName());
        this.setDescription(menuDto.getDescription());
        this.setPrice(menuDto.getPrice());
        this.setCreatedAt(LocalDate.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

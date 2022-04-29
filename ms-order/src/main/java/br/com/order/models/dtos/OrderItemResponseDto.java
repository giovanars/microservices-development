package br.com.order.models.dtos;

public class OrderItemResponseDto {

    private String name;
    private String description;
    private Double price;

    public OrderItemResponseDto() {
    }

    public OrderItemResponseDto(MenuResponse menuById) {
        this.setName(menuById.getName());
        this.setDescription(menuById.getDescription());
        this.setPrice(menuById.getPrice());
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
}

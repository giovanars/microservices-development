package br.com.order.controllers;

import br.com.order.models.dtos.OrderRequestDto;
import br.com.order.models.dtos.OrderResponseDto;
import br.com.order.models.entities.Order;
import br.com.order.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() throws IOException {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable("id") String id) throws IOException {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequestDto request) {
        return ResponseEntity.ok(orderService.create(request));
    }


    @PutMapping("{id}")
    public ResponseEntity<Order> update(@PathVariable("id") String id, @RequestBody OrderRequestDto request) {
        return ResponseEntity.ok(orderService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.ok("Menu deletado");
    }
}

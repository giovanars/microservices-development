package br.com.kitchen.controller;

import br.com.kitchen.logic.OrderLogic;
import br.com.kitchen.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kitchen")
public class OrderController {

    @Autowired
    private OrderLogic orderLogic;

    @PostMapping
    public void orderIsDone(@RequestBody Order order){
        orderLogic.orderIsDone(order);
    }
}

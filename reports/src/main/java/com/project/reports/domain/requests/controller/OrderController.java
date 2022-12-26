package com.project.reports.domain.requests.controller;

import com.project.reports.domain.requests.model.Order;
import com.project.reports.domain.requests.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    @Transactional
    public Order saveOrder(@RequestBody @Validated Order newOrder){
        return orderService.saverOrder(newOrder);
    }

    @GetMapping
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping
    public void deleteAll(){
        orderService.deleteAll();
    }
}

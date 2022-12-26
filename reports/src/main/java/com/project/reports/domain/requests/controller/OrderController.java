package com.project.reports.domain.requests.controller;

import com.project.reports.domain.requests.entity.Order;
import com.project.reports.domain.requests.model.OrderData;
import com.project.reports.domain.requests.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Order> saveOrder(@RequestBody @Valid OrderData newOrder, UriComponentsBuilder uriBuilder){
        var order = orderService.saverOrder(newOrder);
        var uri = uriBuilder.path("order/{codigoPedido}").buildAndExpand(order.getCodigoPedido()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @GetMapping
    public List<Order> findAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{codigoPedido}")
    public Order getOrderById(@PathVariable Long codigoPedido){
        return orderService.getOrderById(codigoPedido);
    }

    @DeleteMapping
    public void deleteAll(){
        orderService.deleteAll();
    }
}

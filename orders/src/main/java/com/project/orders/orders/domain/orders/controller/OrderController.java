package com.project.orders.orders.domain.orders.controller;

import com.project.orders.orders.domain.orders.entity.Order;
import com.project.orders.orders.domain.orders.model.CreatedOrderDetails;
import com.project.orders.orders.domain.orders.model.OrderData;
import com.project.orders.orders.domain.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<CreatedOrderDetails> saveOrder(@RequestBody @Valid OrderData newOrder, UriComponentsBuilder uriBuilder){
        return ResponseEntity.ok(orderService.saverOrder(newOrder));
    }

}

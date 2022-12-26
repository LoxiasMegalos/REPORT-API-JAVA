package com.project.reports.domain.requests.controller;

import com.project.reports.domain.requests.entity.Order;
import com.project.reports.domain.requests.model.OrderData;
import com.project.reports.domain.requests.service.OrderService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiIgnore
    @ApiOperation(value = "", hidden = true)
    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @ApiIgnore
    @ApiOperation(value = "", hidden = true)
    @GetMapping("/{codigoPedido}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long codigoPedido){
        return ResponseEntity.ok(orderService.getOrderById(codigoPedido));
    }

    @ApiIgnore
    @ApiOperation(value = "", hidden = true)
    @DeleteMapping
    public ResponseEntity deleteAll(){
        orderService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

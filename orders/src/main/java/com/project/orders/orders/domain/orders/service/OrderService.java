package com.project.orders.orders.domain.orders.service;

import com.project.orders.orders.domain.orders.entity.Order;
import com.project.orders.orders.domain.orders.model.CreatedOrderDetails;

import com.project.orders.orders.domain.orders.model.OrderData;
import com.project.orders.orders.domain.orders.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public CreatedOrderDetails saverOrder(OrderData newOrder){

        Order order = new Order(newOrder);
        var orderBuscada = orderRepository.findByCodigoPedido(order.getCodigoPedido());

        if(orderBuscada == null){
            var createdOrder = orderRepository.save(order);
            OrderData createdOrderToExchange = new OrderData(createdOrder);
            rabbitTemplate.convertAndSend("orders.ex","",createdOrderToExchange);
            return new CreatedOrderDetails(createdOrder);
        }

        throw new RuntimeException("Numero de pedido ja cadastrado no banco");
    }

}

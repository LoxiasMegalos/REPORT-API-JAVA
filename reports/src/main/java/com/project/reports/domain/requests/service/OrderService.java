package com.project.reports.domain.requests.service;

import com.project.reports.domain.requests.entity.Item;
import com.project.reports.domain.requests.entity.Order;
import com.project.reports.domain.requests.model.OrderData;
import com.project.reports.domain.requests.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order saverOrder(OrderData newOrder){


        Order order = new Order(newOrder);
        var orderBuscada = orderRepository.findByCodigoPedido(order.getCodigoPedido());

        if(orderBuscada == null){
            return orderRepository.save(order);
        }

        throw new RuntimeException("Numero de pedido ja cadastrado no banco");
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void deleteAll(){
        orderRepository.deleteAll();
    }

    public Order getOrderById(Long codigoPedido) {
        return orderRepository.findByCodigoPedido(codigoPedido);
    }
}

package com.project.reports.domain.requests.service;

import com.project.reports.domain.requests.model.Order;
import com.project.reports.domain.requests.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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

    public Order saverOrder(Order order){
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

}

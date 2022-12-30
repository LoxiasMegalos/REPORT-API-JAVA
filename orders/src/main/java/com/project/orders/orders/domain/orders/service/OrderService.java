package com.project.orders.orders.domain.orders.service;

import com.project.orders.orders.domain.orders.entity.Order;
import com.project.orders.orders.domain.orders.model.CreatedOrderDetails;
import com.project.orders.orders.domain.orders.model.ItemData;
import com.project.orders.orders.domain.orders.model.OrderData;
import com.project.orders.orders.domain.orders.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
            rabbitTemplate.convertAndSend("orders.ex","",new OrderData(createdOrder));
            return new CreatedOrderDetails(createdOrder);
        }

        throw new RuntimeException("Numero de pedido ja cadastrado no banco");
    }

    /*
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void deleteAll(){
        orderRepository.deleteAll();
    }

    public Order getOrderById(Long codigoPedido) {
        return orderRepository.findByCodigoPedido(codigoPedido);
    }*/
}

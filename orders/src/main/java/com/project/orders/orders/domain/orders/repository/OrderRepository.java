package com.project.orders.orders.domain.orders.repository;

import com.project.orders.orders.domain.orders.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    @Query("{'codigoPedido': ?0}")
    Order findByCodigoPedido(Long codigoPedido);

    @Query(value = "{codigoCliente:'?0'}")
    List<Order> findAllByCodigoCliente(Long codigoCliente);

    @Query(value = "{'codigoCliente':?0}", count = true)
    Long countByCodigoCliente(Long codigoCliente);

    @Query(value = "{codigoCliente}")
    List<Order> findAllCustomers();
}

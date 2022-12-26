package com.project.reports.domain.requests.repository;

import com.project.reports.domain.requests.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    @Query("{'codigoPedido': ?0}")
    Order findByCodigoPedido(Long codigoPedido);

    @Query(value = "{codigoCliente:'?0'}", fields="{'codigoPedido' : 1}")
    List<String> findAllOrdersByCustomers(Long codigoCliente);

    @Query(value = "{codigoCliente:'?0'}", fields="{'codigoPedido' : 1}", count = true)
    Long findQuantityOrdersByCustomers(Long codigoCliente);

    @Query(value = "{codigoCliente}", fields="{'codigoCliente' : 1}")
    List<Long> findAllCustomers();
}

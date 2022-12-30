package com.project.reports.domain.reports.repository;


import com.project.reports.domain.reports.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportRepository extends MongoRepository<Order, Long> {

    @Query("{'codigoPedido': ?0}")
    Order findByCodigoPedido(Long codigoPedido);

    @Query(value = "{codigoCliente:'?0'}")
    List<Order> findAllByCodigoCliente(Long codigoCliente);

    @Query(value = "{'codigoCliente':?0}", count = true)
    Long countByCodigoCliente(Long codigoCliente);

    @Query(value = "{codigoCliente}")
    List<Order> findAllCustomers();
}

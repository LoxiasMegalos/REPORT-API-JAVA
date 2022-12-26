package com.project.reports.domain.reports.repository;

import com.project.reports.domain.requests.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportRepository extends MongoRepository<Order, Long> {

    @Query("{'codigoPedido': ?0}")
    Order findByCodigoPedido(Long codigoPedido);

}

package com.project.reports.amqp;

import com.project.reports.domain.reports.entity.Order;
import com.project.reports.domain.reports.model.OrderData;
import com.project.reports.domain.reports.repository.ReportRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Autowired
    private ReportRepository reportRepository;

    @RabbitListener(queues = "orders.new-requests")
    public void receberMensagem(OrderData newOrders){
        Order order = new Order(newOrders);
        reportRepository.save(order);
        System.out.println("Salvando pedido: " + newOrders.codigoPedido());
    }
}

package com.project.reports.domain.reports.entity;

import com.project.reports.domain.reports.model.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long codigoPedido;

    private Long codigoCliente;

    private List<Item> itens;

    public Order(OrderData newOrder) {
        this.codigoPedido = newOrder.codigoPedido();
        this.codigoCliente = newOrder.codigoCliente();
        this.itens = newOrder.itens().stream().map(Item::new).toList();
    }
}

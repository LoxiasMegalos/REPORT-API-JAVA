package com.project.orders.orders.domain.orders.entity;

import com.project.orders.orders.domain.orders.model.OrderData;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private List<Item> itens;

    public Order(OrderData newOrder) {
        this.codigoPedido = newOrder.codigoPedido();
        this.codigoCliente = newOrder.codigoCliente();
        this.itens = newOrder.itens();
    }
}
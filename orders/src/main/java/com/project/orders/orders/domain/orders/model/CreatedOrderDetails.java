package com.project.orders.orders.domain.orders.model;

import com.project.orders.orders.domain.orders.entity.Order;

public record CreatedOrderDetails(
        String mensagemPedidoCriado,
        Long ultimoCodigoPedido
) {
    public CreatedOrderDetails(Order createdOrder) {
        this("Pedido criado com sucesso", createdOrder.getCodigoPedido());
    }
}

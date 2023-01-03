package com.project.orders.orders.domain.orders.model;
import com.project.orders.orders.domain.orders.entity.Item;
import com.project.orders.orders.domain.orders.entity.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderData(
        @NotNull(message = "O código do pedido deve ser informado")
        Long codigoPedido,

        @NotNull(message = "O código do cliente deve ser informado")
        Long codigoCliente,

        @NotEmpty
        @Valid
        List<Item> itens
) {

        public OrderData(Order createdOrder) {
                this(createdOrder.getCodigoPedido(), createdOrder.getCodigoCliente(), createdOrder.getItens());
        }
}

package com.project.reports.domain.requests.model;

import com.project.reports.domain.requests.entity.Item;
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

        @NotEmpty(message = "O pedido deve conter no mínimo um item")
        @Valid
        List<ItemData> itens
) {
}

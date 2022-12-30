package com.project.orders.orders.domain.orders.model;

import com.project.orders.orders.domain.orders.entity.Item;
import com.project.orders.orders.domain.orders.entity.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ItemData(
        @NotBlank(message = "O nome do produto deve ser informado")
        String produto,

        @NotNull(message = "Não é possível comprar um item sem quantidade informada")
        Long quantidade,

        @NotNull(message = "O preço deve ser informado")
        Double preco
) {


}

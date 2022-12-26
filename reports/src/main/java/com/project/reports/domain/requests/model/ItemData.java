package com.project.reports.domain.requests.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemData(

        @NotBlank(message = "O nome do produto deve ser informado")
        String produto,

        @NotNull(message = "Não é possível comprar um item sem quantidade informada")
        Long quantidade,

        @NotNull(message = "O preço deve ser informado")
        Double preco
) {
}

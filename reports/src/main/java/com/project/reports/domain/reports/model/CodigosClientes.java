package com.project.reports.domain.reports.model;

import com.project.reports.domain.requests.entity.Order;

public record CodigosClientes(

        Long codigoCliente
) {
    public CodigosClientes(Order order){
        this(order.getCodigoCliente());
    }
}

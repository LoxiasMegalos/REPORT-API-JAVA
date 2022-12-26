package com.project.reports.domain.reports.model;

import com.project.reports.domain.requests.model.Order;

public record DadosValorTotalDoPedido(

        Long codigoPedido,
        Long codigoCliente,
        Double ValorTotal
) {
        public DadosValorTotalDoPedido(Order order, Double valor){
            this(order.getCodigoPedido(), order.getCodigoCliente(), valor);
        }
}

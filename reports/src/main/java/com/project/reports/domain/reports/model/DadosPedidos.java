package com.project.reports.domain.reports.model;

import com.project.reports.domain.requests.model.Item;
import com.project.reports.domain.requests.model.Order;

import java.util.List;

public record DadosPedidos(
        Long numeroPedido,
        List<Item> itens
) {
    public DadosPedidos(Order order){
        this(order.getCodigoPedido(), order.getItens());
    }
}

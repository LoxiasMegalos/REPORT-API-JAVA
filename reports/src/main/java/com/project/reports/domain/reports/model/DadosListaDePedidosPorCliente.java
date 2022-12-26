package com.project.reports.domain.reports.model;
import java.util.List;
public record DadosListaDePedidosPorCliente(

        Long numeroCliente,
        List<DadosPedidos> pedidos
) {

}

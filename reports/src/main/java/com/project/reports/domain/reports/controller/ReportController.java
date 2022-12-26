package com.project.reports.domain.reports.controller;

import com.project.reports.domain.reports.model.DadosListaDePedidosPorCliente;
import com.project.reports.domain.reports.model.DadosQuantidadePedidosPorCliente;
import com.project.reports.domain.reports.model.DadosValorTotalDoPedido;
import com.project.reports.domain.reports.service.ReportService;
import com.project.reports.domain.requests.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping("/pedido/{codigoPedido}")
    public ResponseEntity<DadosValorTotalDoPedido> getValorTotalPedido(@PathVariable Long codigoPedido){
        return ResponseEntity.ok(reportService.getValorTotalDoPedido(codigoPedido));
    }

    @GetMapping("/pedido/quantidade")
    public ResponseEntity<List<DadosQuantidadePedidosPorCliente>> getQuantidadeDePedidosPorCliente(){
        return ResponseEntity.ok(reportService.getQuantidadePedidosPorCliente());
    }

    @GetMapping("/pedido/listagem")
    public ResponseEntity<List<DadosListaDePedidosPorCliente>> getListagemDePedidosPorCliente(){
        return ResponseEntity.ok(reportService.getListaPedidosPorCliente());
    }
}

package com.project.reports.domain.reports.service;

import com.project.reports.domain.reports.entity.Item;
import com.project.reports.domain.reports.entity.Order;
import com.project.reports.domain.reports.model.*;
import com.project.reports.domain.reports.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private ReportRepository reportRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public ReportService(ReportRepository reportRepository, MongoTemplate mongoTemplate){
        this.reportRepository = reportRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public DadosValorTotalDoPedido getValorTotalDoPedido(Long codigoPedido){
        var orderBuscada = reportRepository.findByCodigoPedido(codigoPedido);

        if(orderBuscada == null){
            throw new RuntimeException("Pedido não encontrado no sistema");
        }

        double total = 0.0;

        for(Item item: orderBuscada.getItens()){
            total += item.getPreco()*item.getQuantidade();
        }

        return new DadosValorTotalDoPedido(orderBuscada, total);
    }

    public List<DadosQuantidadePedidosPorCliente> getQuantidadePedidosPorCliente(){

        List<DadosQuantidadePedidosPorCliente> listagem = new ArrayList<DadosQuantidadePedidosPorCliente>();

        Query query = new Query();
        query.addCriteria(Criteria.where("codigoCliente").exists(true));
        var listaCodigoCliente = mongoTemplate.find(query, Order.class).stream().map(CodigosClientes::new).toList();

        Set<CodigosClientes> codigosClientes = new HashSet<CodigosClientes>(listaCodigoCliente);

        for(CodigosClientes codigo : codigosClientes){
            Query queryQuantidadePedidos = new Query();
            queryQuantidadePedidos.addCriteria(Criteria.where("codigoCliente").is(codigo.codigoCliente()));
            var quantidadeDePedidos = mongoTemplate.count(queryQuantidadePedidos, Order.class);

            DadosQuantidadePedidosPorCliente resultado = new DadosQuantidadePedidosPorCliente(codigo.codigoCliente(), quantidadeDePedidos);
            listagem.add(resultado);
        }

        return listagem;
    }

    public List<DadosListaDePedidosPorCliente> getListaPedidosPorCliente(){

        List<DadosListaDePedidosPorCliente> listagem = new ArrayList<DadosListaDePedidosPorCliente>();

        Query query = new Query();
        query.addCriteria(Criteria.where("codigoCliente").exists(true));
        var listaCodigoCliente = mongoTemplate.find(query, Order.class).stream().map(CodigosClientes::new).toList();

        Set<CodigosClientes> codigoClientes = new HashSet<CodigosClientes>(listaCodigoCliente);

        for(CodigosClientes codigo : codigoClientes){
            Query queryListagemPedidos = new Query();
            queryListagemPedidos.addCriteria(Criteria.where("codigoCliente").is(codigo.codigoCliente()));
            var listagemPedidos = mongoTemplate.find(queryListagemPedidos, Order.class).stream().map(DadosPedidos::new).toList();

            DadosListaDePedidosPorCliente resultado = new DadosListaDePedidosPorCliente(codigo.codigoCliente(), listagemPedidos);
            listagem.add(resultado);
        }

        return listagem;
    }
}

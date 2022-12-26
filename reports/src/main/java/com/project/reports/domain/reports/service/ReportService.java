package com.project.reports.domain.reports.service;

import com.project.reports.domain.reports.model.DadosListaDePedidosPorCliente;
import com.project.reports.domain.reports.model.DadosPedidos;
import com.project.reports.domain.reports.model.DadosQuantidadePedidosPorCliente;
import com.project.reports.domain.reports.model.DadosValorTotalDoPedido;
import com.project.reports.domain.reports.repository.ReportRepository;
import com.project.reports.domain.requests.model.Item;
import com.project.reports.domain.requests.model.Order;
import com.project.reports.domain.requests.repository.OrderRepository;
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

    /*
            Query query = new Query();
        query.addCriteria(Criteria.where("codigoCliente").is(order.getCodigoCliente()));
        var query1 = mongoTemplate.find(query, Order.class);

        //var query2 = orderRepository.findQuantityOrdersByCustomers(order.getCodigoCliente());
        //var query3 = orderRepository.findAllCustomers();

        System.out.println("Encontrando todos os pedidos de 1 cliente " + query1);
        //System.out.println("Encontrando a quantidade de pedidos de 1 cliente" + query2);
        //System.out.println("Encontrando todos os clientes" + query3);
     */

    public DadosValorTotalDoPedido getValorTotalDoPedido(Long codigoPedido){
        var orderBuscada = reportRepository.findByCodigoPedido(codigoPedido);

        if(orderBuscada == null){
            throw new RuntimeException("Pedido não encontrado no sistema");
        }

        double total = 0.0;

        for(Item item: orderBuscada.getItens()){
            total += item.getPreco()*item.getQuantidade();
        }
        System.out.println(orderBuscada);
        return new DadosValorTotalDoPedido(orderBuscada, total);
    }

    public List<DadosQuantidadePedidosPorCliente> getQuantidadePedidosPorCliente(){

        List<DadosQuantidadePedidosPorCliente> listagem = new ArrayList<DadosQuantidadePedidosPorCliente>();

        Query query = new Query();
        query.addCriteria(Criteria.where("codigoCliente").exists(true));
        var listaCodigoCliente = mongoTemplate.find(query, Order.class);

        Set<Long> codigoClientes = new HashSet<Long>();

        for(Order order : listaCodigoCliente){
            codigoClientes.add(order.getCodigoCliente());
        }

        for(Long codigo : codigoClientes){
            Query queryQuantidadePedidos = new Query();
            queryQuantidadePedidos.addCriteria(Criteria.where("codigoCliente").is(codigo));
            var quantidadeDePedidos = mongoTemplate.count(queryQuantidadePedidos, Order.class);

            DadosQuantidadePedidosPorCliente resultado = new DadosQuantidadePedidosPorCliente(codigo, quantidadeDePedidos);
            listagem.add(resultado);
        }

        return listagem;
    }

    public List<DadosListaDePedidosPorCliente> getListaPedidosPorCliente(){

        List<DadosListaDePedidosPorCliente> listagem = new ArrayList<DadosListaDePedidosPorCliente>();

        Query query = new Query();
        query.addCriteria(Criteria.where("codigoCliente").exists(true));
        var listaCodigoCliente = mongoTemplate.find(query, Order.class);

        Set<Long> codigoClientes = new HashSet<Long>();

        for(Order order : listaCodigoCliente){
            codigoClientes.add(order.getCodigoCliente());
        }

        for(Long codigo : codigoClientes){
            Query queryListagemPedidos = new Query();
            queryListagemPedidos.addCriteria(Criteria.where("codigoCliente").is(codigo));
            var listagemPedidos = mongoTemplate.find(queryListagemPedidos, Order.class).stream().map(DadosPedidos::new).toList();


            DadosListaDePedidosPorCliente resultado = new DadosListaDePedidosPorCliente(codigo, listagemPedidos);
            listagem.add(resultado);
        }

        return listagem;
    }
}

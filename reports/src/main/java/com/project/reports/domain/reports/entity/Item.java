package com.project.reports.domain.reports.entity;

import com.project.reports.domain.reports.model.ItemData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("itens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String produto;

    private Long quantidade;

    private Double preco;

    public Item(ItemData itemData) {
        this.preco = itemData.preco();
        this.produto = itemData.produto();
        this.quantidade = itemData.quantidade();
    }


    public Item(Item item) {
        this.preco = item.getPreco();
        this.produto = item.getProduto();
        this.quantidade = item.getQuantidade();
    }
}

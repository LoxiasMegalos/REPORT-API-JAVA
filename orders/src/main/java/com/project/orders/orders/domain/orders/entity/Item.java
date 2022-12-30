package com.project.orders.orders.domain.orders.entity;

import com.project.orders.orders.domain.orders.model.ItemData;
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
}

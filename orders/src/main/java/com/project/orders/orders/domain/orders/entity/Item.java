package com.project.orders.orders.domain.orders.entity;

import com.project.orders.orders.domain.orders.model.ItemData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @NotNull
    @Size(min = 1)
    private String produto;
    @NotBlank
    @NotNull
    @Positive
    private Long quantidade;
    @NotBlank
    @NotNull
    @Positive
    private Double preco;

    public Item(ItemData itemData) {
        this.preco = itemData.preco();
        this.produto = itemData.produto();
        this.quantidade = itemData.quantidade();
    }
}

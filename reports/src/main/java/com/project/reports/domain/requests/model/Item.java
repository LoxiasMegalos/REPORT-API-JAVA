package com.project.reports.domain.requests.model;

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

}

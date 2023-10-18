package com.example.aula3.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdutoDTO {
    private Long id;
    private String prod_nome;
    private Integer prod_qtd;
    private Integer categoriaProdutoId;
}

package com.example.aula3.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaProdutoDTO {
    private Integer id;
    private String cat_nome;
    private String cat_descricao;
}
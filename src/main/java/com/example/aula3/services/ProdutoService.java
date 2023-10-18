package com.example.aula3.services;

import java.util.List;

import com.example.aula3.dtos.DadosProdutoDTO;
import com.example.aula3.dtos.ProdutoDTO;
import com.example.aula3.models.Produto;

public interface ProdutoService {
    Produto inserir(ProdutoDTO dto);
    List<ProdutoDTO> obterTodos();
    DadosProdutoDTO obterPorId(Long id);
    void excluir(Long id);
    void editar(Long id, ProdutoDTO dto);
}
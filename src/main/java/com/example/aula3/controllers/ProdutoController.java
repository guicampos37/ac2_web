package com.example.aula3.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula3.dtos.DadosProdutoDTO;
import com.example.aula3.dtos.ProdutoDTO;
import com.example.aula3.services.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    private ProdutoService pService;

    public ProdutoController(ProdutoService pService) {
        this.pService = pService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long inserir(@RequestBody ProdutoDTO produtoDTO) {
        return pService.inserir(produtoDTO).getId();
    }

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return pService.obterTodos();
    }

    @GetMapping("{id}")
    public DadosProdutoDTO obterPorId(@PathVariable Long id) {
        return pService.obterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pService.excluir(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        pService.editar(id, dto);
    }

}
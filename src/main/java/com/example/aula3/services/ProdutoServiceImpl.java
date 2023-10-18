package com.example.aula3.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.aula3.dtos.CategoriaProdutoDTO;
import com.example.aula3.dtos.DadosProdutoDTO;
import com.example.aula3.dtos.ProdutoDTO;
import com.example.aula3.exceptions.RegraNegocioException;
import com.example.aula3.models.CategoriaProduto;
import com.example.aula3.models.Produto;
import com.example.aula3.repository.CategoriaProdutoRepository;
import com.example.aula3.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    @Override
    public Produto inserir(ProdutoDTO produtoDTO) {
        CategoriaProduto categ = categoriaProdutoRepository.findById(
            produtoDTO.getCategoriaProdutoId()).orElseThrow(
                        () -> new RegraNegocioException("Código da categoria não encontrado!"));

        Produto p = new Produto();
        p.setProd_qtd(produtoDTO.getProd_qtd());
        p.setCategoriaProduto(categ);
        p.setProd_nome(produtoDTO.getProd_nome());
        return produtoRepository.save(p);
    }

    public List<ProdutoDTO> obterTodos() {
        List<ProdutoDTO> produtos = produtoRepository.findAll().stream().map(
            (Produto p) -> {
                return ProdutoDTO.builder()
                .id(p.getId())
                .prod_nome(p.getProd_nome())
                .prod_qtd(p.getProd_qtd())
                .categoriaProdutoId(p.getCategoriaProduto() == null ? 0 : p.getCategoriaProduto().getId()).build();
            }
        ).collect(Collectors.toList());
        return produtos;
    }

    public DadosProdutoDTO obterPorId(Long id) {
        return produtoRepository.findById(id).map((Produto p) -> {
            return DadosProdutoDTO.builder()
                .id(p.getId())
                .prod_nome(p.getProd_nome())
                .prod_qtd(p.getProd_qtd())
                .categoria(p.getCategoriaProduto() != null ? 
                CategoriaProdutoDTO.builder()
                    .id(p.getCategoriaProduto().getId())
                    .cat_nome(p.getCategoriaProduto().getCat_nome())
                    .cat_descricao(p.getCategoriaProduto().getCat_descricao())
                .build() : null
            ).build();
        }).orElseThrow(()->new RegraNegocioException("Id do curso não encontrado"));
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public void editar(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new RegraNegocioException("Código usuário não encontrado."));
        CategoriaProduto categoriaProduto =
        categoriaProdutoRepository.findById(dto.getCategoriaProdutoId())
        .orElseThrow(() -> new RegraNegocioException("Categoria não encontrado."));
        produto.setProd_nome(dto.getProd_nome());
        produto.setProd_qtd(dto.getProd_qtd());
        produto.setCategoriaProduto(categoriaProduto);
        produtoRepository.save(produto);
    }

}
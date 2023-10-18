package com.example.aula3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.aula3.models.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Integer> {
    @Query("select cc from CategoriaProduto cc left join fetch cc.produtos c " +
    "where cc.id = :id ")
    CategoriaProduto findCategoriaProdutoFetchProduto(@Param("id") Long id);
}

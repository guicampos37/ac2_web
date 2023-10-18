package com.example.aula3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aula3.models.CategoriaProduto;
import com.example.aula3.repository.CategoriaProdutoRepository;
import com.example.aula3.models.Produto;
import com.example.aula3.repository.ProdutoRepository;

@SpringBootApplication
public class Aula3Application {

	@Bean
	public CommandLineRunner init(
			@Autowired ProdutoRepository pRepository,
			@Autowired CategoriaProdutoRepository catProdRepository) {
		return args -> {
			pRepository.save(
					new Produto((long) 0, "produto1", 1000));
			pRepository.save(
					new Produto((long) 0, "produto2", 1200));
			List<Produto> listaProdutos = pRepository.findAll();
			// listaProdutos.forEach(System.out::println);

			// System.out.println("** Exemplo obter por nome **");
			// listaCursos = cursoRepository.findByNomeLike("%2%");
			// listaCursos.forEach(System.out::println);

			CategoriaProduto cat1 = new CategoriaProduto(null, "categoria1", "Primeira Categoria");
			CategoriaProduto cat2 = new CategoriaProduto(null, "categoria2", "Segunda Categoria");
			catProdRepository.save(cat1);
			catProdRepository.save(cat2);

			listaProdutos.get(0).setCategoriaProduto(cat1);
			listaProdutos.get(1).setCategoriaProduto(cat2);
			pRepository.save(listaProdutos.get(0));
			pRepository.save(listaProdutos.get(1));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Aula3Application.class, args);
	}

}

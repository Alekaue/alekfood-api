package com.alekaue.alekfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alekaue.alekfood.api.assember.ProdutoInputDisassembler;
import com.alekaue.alekfood.api.assember.ProdutoModelAssembler;
import com.alekaue.alekfood.api.model.ProdutoModel;
import com.alekaue.alekfood.api.model.input.ProdutoInput;
import com.alekaue.alekfood.domain.model.Produto;
import com.alekaue.alekfood.domain.model.Restaurante;
import com.alekaue.alekfood.domain.repository.ProdutoRepository;
import com.alekaue.alekfood.domain.service.CadastroProdutoService;
import com.alekaue.alekfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativos ) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		List<Produto> todosProdutos = null; 
		
		if (incluirInativos) {
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
			
		
		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable	 Long produtoId) {
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		
		produto = cadastroProduto.salvar(produto);
		
		return produtoModelAssembler.toModel(produto);
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		produtoInputDisassembler.copyDomainObject(produtoInput, produtoAtual);
		
		produtoAtual = cadastroProduto.salvar(produtoAtual);
		
		return produtoModelAssembler.toModel(produtoAtual);
	}
}

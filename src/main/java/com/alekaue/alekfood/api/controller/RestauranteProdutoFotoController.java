package com.alekaue.alekfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alekaue.alekfood.api.assember.FotoProdutoModelAssembler;
import com.alekaue.alekfood.api.model.FotoProdutoModel;
import com.alekaue.alekfood.api.model.input.FotoProdutoInput;
import com.alekaue.alekfood.domain.model.FotoProduto;
import com.alekaue.alekfood.domain.model.Produto;
import com.alekaue.alekfood.domain.service.CadastroProdutoService;
import com.alekaue.alekfood.domain.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoAssembler;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel	 atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@Valid FotoProdutoInput fotoProdutoInput) {
		
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto);
		
		return fotoProdutoAssembler.toModel(fotoSalva);
	}

}

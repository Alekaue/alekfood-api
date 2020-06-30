package com.alekaue.alekfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alekaue.alekfood.domain.exception.NegocioException;
import com.alekaue.alekfood.domain.exception.PedidoNaoEncontradoException;
import com.alekaue.alekfood.domain.model.Cidade;
import com.alekaue.alekfood.domain.model.FormaPagamento;
import com.alekaue.alekfood.domain.model.Pedido;
import com.alekaue.alekfood.domain.model.Produto;
import com.alekaue.alekfood.domain.model.Restaurante;
import com.alekaue.alekfood.domain.model.Usuario;
import com.alekaue.alekfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido emitir(Pedido pedido) {
		
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		
		return pedidoRepository.save(pedido);
	}
	
	private void validarPedido(Pedido pedido) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario usuario = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());
		
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setCliente(usuario);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		
		if(restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.", formaPagamento.getDescricao()));
		}
	}
	
	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProduto.buscarOuFalahr(
					pedido.getRestaurante().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}

	public Pedido buscarOuFalhar(String codidoPedido) {
		return pedidoRepository.findByCodigo(codidoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codidoPedido));
	}
}

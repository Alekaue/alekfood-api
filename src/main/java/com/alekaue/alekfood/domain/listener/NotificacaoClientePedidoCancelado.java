package com.alekaue.alekfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.alekaue.alekfood.domain.event.PedidoCanceladoEvent;
import com.alekaue.alekfood.domain.model.Pedido;
import com.alekaue.alekfood.domain.service.EnvioEmailService;
import com.alekaue.alekfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCancelado {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmail.enviar(mensagem);

	}
}

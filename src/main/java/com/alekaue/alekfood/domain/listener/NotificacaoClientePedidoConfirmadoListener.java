package com.alekaue.alekfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.alekaue.alekfood.domain.event.PedidoConfirmadoEvent;
import com.alekaue.alekfood.domain.model.Pedido;
import com.alekaue.alekfood.domain.service.EnvioEmailService;
import com.alekaue.alekfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmaPedido(PedidoConfirmadoEvent event) {
		
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		
		envioEmail.enviar(mensagem);
	}

}

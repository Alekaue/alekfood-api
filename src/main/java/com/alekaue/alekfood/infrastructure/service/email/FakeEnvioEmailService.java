package com.alekaue.alekfood.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

	@Override
	public void enviar(Mensagem mensagem) {
		String corpo = processarTemplate(mensagem);
		
		log.info("[FAKE E-EMAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
	}

}

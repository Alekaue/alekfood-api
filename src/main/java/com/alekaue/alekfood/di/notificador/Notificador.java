package com.alekaue.alekfood.di.notificador;

import com.alekaue.alekfood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}
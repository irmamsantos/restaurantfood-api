package com.irmamsantos.restaurantfood.di.notificacao;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}
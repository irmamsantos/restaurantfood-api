package com.irmamsantos.restaurantfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.irmamsantos.restaurantfood.domain.event.PedidoCanceladoEvent;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.service.EnvioEmailService;
import com.irmamsantos.restaurantfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {
	
	@Autowired
	private EnvioEmailService envioEmail;

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmarPedido(PedidoCanceladoEvent event) {
		
		Pedido pedido = event.getPedido();

		Mensagem mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido Cancelado")
				.corpo("pedido-cancelado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
	}
}

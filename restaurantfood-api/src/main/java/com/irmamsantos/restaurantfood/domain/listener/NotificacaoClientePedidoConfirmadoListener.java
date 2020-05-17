package com.irmamsantos.restaurantfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.irmamsantos.restaurantfood.domain.event.PedidoConfirmadoEvent;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.service.EnvioEmailService;
import com.irmamsantos.restaurantfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmail;

	/*
	 * Usando @EventListener os eventos são gerados antes do update. E se update der erro, o mail já foi 
	 * enviado. Se usar @TransactionalEventListener os eventos são gerados depois do commit, por omissão 
	 * é AFTER_COMMIT. Mas neste caso fez commit e pode dar um erro no envio de mail. Cabe decidir se o 
	 * mail é mesmo importante dentro da confirmação. Caso seja mesmo importante ao ponto de fazer rollback 
	 * da confirmação caso não envie mail então usa-se BEFORE_COMMIT.
	 */
	
	//@EventListener
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		
		Pedido pedido = event.getPedido();

		Mensagem mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
//				.corpo("O pedido de código <strong>"
//						+ pedido.getCodigo() + "</strong> foi confirmado!")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
	}
}

package com.irmamsantos.restaurantfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
/*	
	@Autowired
	private EnvioEmailService envioEmail;
*/
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
		
/* Código movido para NotificacaoClientePedidoConfirmadoListener
  	
		Mensagem mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
//				.corpo("O pedido de código <strong>"
//						+ pedido.getCodigo() + "</strong> foi confirmado!")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
*/		
		/*
		 * Não seria necessário o save para gravar o pedido porque fez get e está ser gerido pelo
		 * EntityManager. A necessidade do save é para disparar o evento que está registado em 
		 * Pedido. É como está feito a implementação do spring face ao disparo do evento.
		 */
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
	    pedido.cancelar();
	}

	@Transactional
	public void entregar(String codigoPedido) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
	    pedido.entregar();
	}
}

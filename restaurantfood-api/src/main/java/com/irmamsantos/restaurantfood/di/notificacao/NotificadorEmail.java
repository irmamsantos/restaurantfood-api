package com.irmamsantos.restaurantfood.di.notificacao;


import com.irmamsantos.restaurantfood.di.modelo.Cliente;

//instancia o bean pelo Spring para injectar no AtivacaoClienteService
//@Component
//foi comentado por vai ser criado a instancia através anotação @bean
public class NotificadorEmail implements Notificador  {
	
	private boolean upperCase;
	private String hostServidorSmtp;
	
	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}

	/* (non-Javadoc)
	 * @see com.irmamsantos.restaurantfood.di.notificacao.Notificador#notificar(com.irmamsantos.restaurantfood.di.modelo.Cliente, java.lang.String)
	 */
	public void notificar(Cliente cliente, String mensagem) {
		
		if (this.upperCase) {
			mensagem = mensagem.toUpperCase();
		}
		
		System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), this.hostServidorSmtp, mensagem);
	}

	public void setUpperCase(boolean upperCase) {
		this.upperCase = upperCase;
	}
}

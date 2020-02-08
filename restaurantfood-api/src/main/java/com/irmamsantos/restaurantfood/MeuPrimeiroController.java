package com.irmamsantos.restaurantfood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente joao = new Cliente("Joãoo", "joao@xyz.com", "3499998888");
		
		this.ativacaoClienteService.ativar(joao);
		
		return "Hello";
	}
}

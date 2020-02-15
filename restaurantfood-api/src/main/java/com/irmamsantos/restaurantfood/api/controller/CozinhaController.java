package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository; 
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.todas();
	}

}

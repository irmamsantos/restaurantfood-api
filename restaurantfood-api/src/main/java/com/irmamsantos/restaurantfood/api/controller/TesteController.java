package com.irmamsantos.restaurantfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.CozinhaRepository;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(
			@RequestParam("taxaInicial") BigDecimal taxaFreteInicial,
			@RequestParam("taxaFinal") BigDecimal taxaFreteFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNomeCozinha(
			@RequestParam("nome") String nome,
			@RequestParam("cozinhaId") Long cozinhaId) {
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}
}

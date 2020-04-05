package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.ProdutoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.ProdutoInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.ProdutoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.ProdutoDTO;
import com.irmamsantos.restaurantfood.domain.model.Produto;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.service.ProdutoService;
import com.irmamsantos.restaurantfood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos") 
public class RestauranteProdutoController {
	
	@Autowired
	private RestauranteService restauranteService; 
	
	@Autowired
	private ProdutoService produtoService; 
	
	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler; 
	
	@Autowired
	private ProdutoInputDTODisassembler produtoInputDTODisassembler; 
	
	//Nesta implementação faz gestão de produtos um a um apartir de restaurantes...
	// GET /restaurantes/{id}/produtos
	// PUT /restaurantes/{id}/produtos/{id}
	// DELETE /restaurantes/{id}/produtos/{id}

	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId) {
		
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		return produtoDTOAssembler.toCollectionDTO(restaurante.getProdutos());
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
		return produtoDTOAssembler.toDTO(produto);
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO adicionar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoInputDTO produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        
        Produto produto = produtoInputDTODisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        
        produto = produtoService.salvar(produto);
        
        return produtoDTOAssembler.toDTO(produto);
    }
    
    @PutMapping("/{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoInputDTO produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);
        
        produtoInputDTODisassembler.copyToDomainObject(produtoInput, produtoAtual);
        
        produtoAtual = produtoService.salvar(produtoAtual);
        
        return produtoDTOAssembler.toDTO(produtoAtual);
    }   	
}

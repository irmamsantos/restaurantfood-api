package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.CidadeDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.CidadeInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.CidadeInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.CidadeDTO;
import com.irmamsantos.restaurantfood.domain.exception.CidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EstadoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.repository.CidadeRepository;
import com.irmamsantos.restaurantfood.domain.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades") 
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository; 
	
	@Autowired
	private CidadeService cidadeService; 
	
	@Autowired
	private CidadeDTOAssembler cidadeDTOAssembler;
	
	@Autowired
	private CidadeInputDTODisassembler cidadeInputDTODisassembler;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CidadeDTO> listar() {
		return cidadeDTOAssembler.toCollectionDTO(cidadeRepository.findAll());
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable("cidadeId") Long id) 
			throws CidadeNaoEncontradaException {
		
		return cidadeDTOAssembler.toDTO(cidadeService.buscarOuFalhar(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInputDTO cidadeInput) throws NegocioException {
		try {
			Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInput);
			return cidadeDTOAssembler.toDTO(cidadeService.salvar(cidade));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeDTO actualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputDTO cidadeInput) 
			throws CidadeNaoEncontradaException, NegocioException {
		
		Cidade cidadeActual = cidadeService.buscarOuFalhar(cidadeId);
	
		//Para não estar identificar os campos que não quer copiar de forma explicita
		//deixa de usar o BeansUtils e passa usar o ModelMapper em que o destino terá apenas os campos
		//que pretende actualizar		
//		BeanUtils.copyProperties(cidade, cidadeActual, "id");
		
		cidadeInputDTODisassembler.copyToDomainObject(cidadeInput, cidadeActual);
		
		try {
			return cidadeDTOAssembler.toDTO(cidadeService.salvar(cidadeActual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	} 
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) 
			throws CidadeNaoEncontradaException, EntidadeEmUsoException {
		cidadeService.excluir(cidadeId);
	}
	
/*	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
		
		try {
			cidadeService.excluir(cidadeId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}	
*/

}

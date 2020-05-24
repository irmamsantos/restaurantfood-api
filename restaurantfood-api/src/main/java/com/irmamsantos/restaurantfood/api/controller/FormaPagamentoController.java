package com.irmamsantos.restaurantfood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.irmamsantos.restaurantfood.api.assembler.FormaPagamentoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.FormaPagamentoInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.FormaPagamentoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.FormaPagamentoDTO;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.model.FormaPagamento;
import com.irmamsantos.restaurantfood.domain.repository.FormaPagamentoRepository;
import com.irmamsantos.restaurantfood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository; 
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@Autowired
	private FormaPagamentoInputDTODisassembler formaPagamentoInputDTODisassembler;	
	
	@GetMapping
	public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest webRequest) {
		
		//não usou outra implementacao para o Deep Tag, reutilizou o ShallowEtagHeaderFilter
		//desabilitando caching gerido pela mesma implementacao
		ShallowEtagHeaderFilter.disableContentCaching(webRequest.getRequest());
		
		//valor inicial, por omissão caso não haja data de actualizacao, tabela vazia
		String eTag = "0";
		
		OffsetDateTime dataUltimaActualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();
		
		if (dataUltimaActualizacao != null) {
			eTag = String.valueOf(dataUltimaActualizacao.toEpochSecond());
		}
		
		//neste momento já têm condições para saber se continua ou não o processamento
		if (webRequest.checkNotModified(eTag)) {
			//null ????? Porque não devolve o status 304 !?!?
			return null;
		}
		
		List<FormaPagamentoDTO> formaPagamentos = formaPagamentoDTOAssembler
				.toCollectionDTO(formaPagamentoRepository.findAll());
		
		return ResponseEntity.ok()
				// Cache-control: max-age=10
				//poderia fazer com .header("cache-control", headerValues) mas já existe um método
				//que permite fazer mais via objectos...
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)
				
				//.cachePrivate() só faz sentido para dados especificos de um dado utilizador,
				//faz sentido fazer cache mas não partilhados entre todos os utilizadores
				//.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
				
				//.cachePublic() é o valor por omissão e tanto é usado como cache local como
				//cache partilhado
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
				
				//este nome engana, esta função implica a fazer sempre validação etag na resposta
				//como estivesse em stale (dados velhos)
				//ou seja faz select de validação no serviço mas não responde nenhuma resposta (código status 
				//304) porque não houve nenhuma alteração da resposta, ou seja é a mesma resposta cacheada 
				//localmente no browser
				//.cacheControl(CacheControl.noCache())
				
				//esta opção indica mesmo que a resposta não pode ser cached tanto local como partilhado
				//.cacheControl(CacheControl.noStore())
				
				//também poderia fazer-se assim
				//.header("ETag", eTag)
				.eTag(eTag)
				.body(formaPagamentos);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable("formaPagamentoId") Long id) 
			throws EntidadeNaoEncontradaException {
		
		FormaPagamentoDTO formaPagamento = formaPagamentoDTOAssembler
				.toDTO(formaPagamentoService.buscarOuFalhar(id));
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				//poderia ser feito aqui mas existe uma implementação que já isto
				//shallowEtagHeaderFilter()
				//.eTag("dhgd6565d656vv")
				.body(formaPagamento);
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDTODisassembler.toDomainObject(formaPagamentoInput);
	
		return formaPagamentoDTOAssembler.toDTO(formaPagamentoService.salvar(formaPagamento));
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO actualizar(@PathVariable Long formaPagamentoId, 
				@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInput) 
			throws EntidadeNaoEncontradaException, NegocioException {
		try {
			FormaPagamento formaPagamentoActual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

			formaPagamentoInputDTODisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoActual);

			return formaPagamentoDTOAssembler.toDTO(formaPagamentoService.salvar(formaPagamentoActual));
			
		} catch (FormaPagamentoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	} 
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) 
			throws EntidadeNaoEncontradaException, EntidadeEmUsoException {
		
		formaPagamentoService.excluir(formaPagamentoId);
	}	
}

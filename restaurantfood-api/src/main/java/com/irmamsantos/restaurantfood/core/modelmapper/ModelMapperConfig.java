package com.irmamsantos.restaurantfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irmamsantos.restaurantfood.api.model.dto.input.ItemPedidoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.RestauranteDTO;
import com.irmamsantos.restaurantfood.domain.model.ItemPedido;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		//org.springframework.dao.InvalidDataAccessApiUsageException: detached entity passed to 
		//persist: com.irmamsantos.restaurantfood.domain.model.ItemPedido
		//porque estava a mapear productId em Id no ItemPedido
		modelMapper.createTypeMap(ItemPedidoInputDTO.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		//customizar os mapeamentos de campos entre a origem/destino
		TypeMap<Restaurante, RestauranteDTO> baseAddMApping = modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		
		//<V> TypeMap<S, D> addMapping(SourceGetter<S> sourceGetter, DestinationSetter<D, V> destinationSetter)
		//não funciona se criar mais do 1 modelMapper.createTypeMap, tenho de usar como base baseAddMApping
		//modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class)
		baseAddMApping
			.<String>addMapping(
				/*implementação interfaces funcionais com lambda*/
				//enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				restSrc -> restSrc.getEndereco().getCidade().getEstado().getNome(),
				/*implementação interfaces funcionais com lambda*/
				(restDTODest, valorACopiar) -> restDTODest.getEndereco().getCidade().setEstado(valorACopiar));
		
		return modelMapper;
	}
}

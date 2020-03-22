package com.irmamsantos.restaurantfood.api.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.irmamsantos.restaurantfood.api.model.dto.output.CozinhaDTO;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName="cozinhas")
@Data
public class CozinhasXmlWrapper {
	
	@JacksonXmlProperty(localName="cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull
	private List<CozinhaDTO> cozinhas;

}

package com.irmamsantos.restaurantfood.core.squiggly;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

@Configuration
public class SquigglyConfig {

	@Bean
	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(
			ObjectMapper objectMapper) {
		
		//pode definir os endpoints que quer aplicar os filtros por Squiggly
		List<String> urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");
		
		//por omissão é "fiels", mudou o param para "campos"
		Squiggly.init(objectMapper, new RequestSquigglyContextProvider("campos", null));
		
		FilterRegistrationBean<SquigglyRequestFilter> filterRegistration =
				new FilterRegistrationBean<SquigglyRequestFilter>();
		
		filterRegistration.setFilter(new SquigglyRequestFilter());
		filterRegistration.setOrder(1);
		filterRegistration.setUrlPatterns(urlPatterns);
		
		return filterRegistration;
	}
}

package com.irmamsantos.restaurantfood.core.web;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	//Configuração do cors transversal no projecto
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//para todos os mapeamentos nos controllers
		registry.addMapping("/**")
			//por omissão aceita GET, HEAD, PUT, mas neste caso define-se para todos
			.allowedMethods("*")
			//definição de 
			.allowedOrigins("*")
			//por omissão são 30 minutos
			//.maxAge(10)
			;
	}
	
	//esta implementação gera o hash usado para comparar conteúdos quando passa de fresh(actualizada) 
	//para stale(velha)
	//tudo é controlado por esta implementação
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
}

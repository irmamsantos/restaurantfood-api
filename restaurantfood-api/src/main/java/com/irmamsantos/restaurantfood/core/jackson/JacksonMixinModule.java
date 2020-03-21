package com.irmamsantos.restaurantfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.irmamsantos.restaurantfood.api.model.mixinResta.RestauranteMixin;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 848593890197625276L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}
}

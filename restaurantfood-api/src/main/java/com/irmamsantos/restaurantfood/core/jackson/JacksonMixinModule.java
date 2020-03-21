package com.irmamsantos.restaurantfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.irmamsantos.restaurantfood.api.model.mixin.CozinhaMixin;
import com.irmamsantos.restaurantfood.api.model.mixin.GrupoMixin;
import com.irmamsantos.restaurantfood.api.model.mixin.PedidoMixin;
import com.irmamsantos.restaurantfood.api.model.mixin.RestauranteMixin;
import com.irmamsantos.restaurantfood.api.model.mixin.UsuarioMixin;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Grupo;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.model.Usuario;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 848593890197625276L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Grupo.class, GrupoMixin.class);
		setMixInAnnotation(Pedido.class, PedidoMixin.class);
		setMixInAnnotation(Usuario.class, UsuarioMixin.class);
	}
}

package com.irmamsantos.restaurantfood.domain.service;

import com.irmamsantos.restaurantfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}

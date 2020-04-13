package com.irmamsantos.restaurantfood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.filter.VendaDiariaFilter;
import com.irmamsantos.restaurantfood.domain.service.VendaReportService;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		return null;
	}
}

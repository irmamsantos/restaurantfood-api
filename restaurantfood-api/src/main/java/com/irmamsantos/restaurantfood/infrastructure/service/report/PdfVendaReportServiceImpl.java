package com.irmamsantos.restaurantfood.infrastructure.service.report;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.filter.VendaDiariaFilter;
import com.irmamsantos.restaurantfood.domain.model.dto.VendaDiaria;
import com.irmamsantos.restaurantfood.domain.service.VendaQueryService;
import com.irmamsantos.restaurantfood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		try {
			//this.getClass() dá o classpath
			var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas_diarias.jasper");

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("REPORT_LOCALE", new Locale("pt","PT"));

			List<VendaDiaria> vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendasDiarias);
			var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possivel emitir relatório de vendas diárias", e);
		}
	}
}

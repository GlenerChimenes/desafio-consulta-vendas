package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.util.UtilDate;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	LocalDate dataInicial2 = null;
	LocalDate dataFinal2 = null;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(String dataInicial, String dataFinal, String nome, Pageable pageable) {
		
		LocalDate dataInicial2 = null;
		LocalDate dataFinal2 = null;
		
		if(dataFinal.equals("") ) {
			dataFinal2 = UtilDate.buscarDataSistema();
		}
		else {
			dataFinal2 = stringToLocalDate(dataFinal);
		}
		if(dataInicial.equals("")) {
			dataInicial2 = UtilDate.jogarDatasistema1AnoAtras(dataFinal2);
			
		}else {
			dataInicial2 =  stringToLocalDate(dataInicial);
		}
		
		Page<Sale> result = repository.getReport(dataInicial2, dataFinal2, nome, pageable);
        return result.map(x -> new SaleMinDTO(x));
	}

	private LocalDate stringToLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(data, formatter);

	}

}

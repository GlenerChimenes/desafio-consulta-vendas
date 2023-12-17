package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SellerDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import com.devsuperior.dsmeta.util.UtilDate;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;
	

	public List<SellerDTO> getSummary(String dataInicial, String dataFinal) {
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
		List<Seller> result = repository.getSummary(dataInicial2, dataFinal2);
        return result.stream().map(x -> new SellerDTO(x)).toList();
        
	}
	
	private LocalDate stringToLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(data, formatter);

	}
}

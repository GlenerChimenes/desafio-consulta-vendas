package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerDTO;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SellerService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = saleService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(name = "dataInicial", defaultValue = "") String dataInicial,
													  @RequestParam(name = "dataFinal", defaultValue = "") String dataFinal,
													  @RequestParam(name = "nome", defaultValue = "") String nome,Pageable pageable) {
		Page<SaleMinDTO> report = saleService.getReport(dataInicial, dataFinal, nome, pageable);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerDTO>> getSummary(@RequestParam(name = "dataInicial", defaultValue = "") String dataInicial,
			                                    @RequestParam(name = "dataFinal", defaultValue = "") String dataFinal) {
		List<SellerDTO> report = sellerService.getSummary(dataInicial, dataFinal);
		return ResponseEntity.ok(report);
	}
}

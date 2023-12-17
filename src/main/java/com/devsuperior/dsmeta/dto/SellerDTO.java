package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SellerDTO {

	private String nome;
	private Double total = 0.0;
	
	public SellerDTO() {
		
	}
	
	public SellerDTO(Seller entity) {
		nome = entity.getName();
		for (Sale sale :  entity.getSales()) {
			total += sale.getAmount();
		}
	}
	
	public String getNome() {
		return nome;
	}

	public Double getTotal() {
		return total;
	}

}

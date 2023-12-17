package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

	@Query("SELECT DISTINCT s FROM Seller s JOIN FETCH s.sales sale " +
            "WHERE sale.date between :dataInicial and :dataFinal " )
	List<Seller> getSummary(LocalDate dataInicial, LocalDate dataFinal);
	
}

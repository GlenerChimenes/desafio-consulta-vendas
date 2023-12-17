package com.devsuperior.dsmeta.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UtilDate {
	

	public static LocalDate buscarDataSistema() {
		return  LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	}
	
	public static LocalDate  jogarDatasistema1AnoAtras(LocalDate dataFinal) {
		return dataFinal.minusYears(1L);
	}

}

package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Businessinformatics.model.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{

	@Query("select ce.middleRate from CurrencyExchange as ce where ce.primaryCurrency.officialCode=?1 and ce.accordingToCurrency.officialCode='DIN'")
	double findMiddleRateAccordingToDinars(String code);

	@Query("select ce.middleRate from CurrencyExchange as ce where ce.primaryCurrency.officialCode=?1 and ce.accordingToCurrency.officialCode=?2")
	double findMiddleRate(String code, String accordingTo);
	
}

package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Businessinformatics.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	
	public Currency findByName(String name);
}

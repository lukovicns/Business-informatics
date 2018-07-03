package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.CurrencyExchange;

public interface CurrencyExchangeService {

	public ArrayList<CurrencyExchange> getAll();
	public void save(CurrencyExchange ce);
	public void removeCurrencyExchange(Long id);
}

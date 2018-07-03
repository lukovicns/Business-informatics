package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.Currency;

public interface CurrencyService {
	
	public ArrayList<Currency> getAll();
	public void save(Currency currency);
	public void removeCurrency(Long id);
	Currency getCurrency(Long id);
	
	
}

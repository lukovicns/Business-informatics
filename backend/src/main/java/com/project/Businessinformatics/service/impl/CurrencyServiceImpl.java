package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.Currency;
import com.project.Businessinformatics.repository.CurrencyRepository;
import com.project.Businessinformatics.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Override
	public ArrayList<Currency> getAll() {
		return (ArrayList<Currency>) currencyRepository.findAll();
	}

	@Override
	public void save(Currency currency) {
		currencyRepository.save(currency);
	}

	@Override
	public void removeCurrency(Long id) {
		currencyRepository.deleteById(id);
	}

	@Override
	public Currency getCurrency(Long id) {
		return currencyRepository.getOne(id);
	}

}

package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.CurrencyExchange;
import com.project.Businessinformatics.repository.CurrencyExchangeRepository;
import com.project.Businessinformatics.service.CurrencyExchangeService;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{
	
	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;

	@Override
	public ArrayList<CurrencyExchange> getAll() {
		return (ArrayList<CurrencyExchange>) currencyExchangeRepository.findAll();
	}

	@Override
	public void save(CurrencyExchange ce) {
		currencyExchangeRepository.save(ce);
	}

	@Override
	public void removeCurrencyExchange(Long id) {
		currencyExchangeRepository.deleteById(id);
	}
}

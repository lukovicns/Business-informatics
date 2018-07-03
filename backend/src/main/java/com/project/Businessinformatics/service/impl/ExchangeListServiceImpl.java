package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.ExchangeList;
import com.project.Businessinformatics.repository.ExchangeListRepository;
import com.project.Businessinformatics.service.ExchangeListService;

@Service
public class ExchangeListServiceImpl implements ExchangeListService {

	@Autowired
	private ExchangeListRepository exchangeListRepository;
	
	@Override
	public ArrayList<ExchangeList> getAll() {
		return (ArrayList<ExchangeList>) exchangeListRepository.findAll();
	}

	@Override
	public void save(ExchangeList exchangeList) {
		exchangeListRepository.save(exchangeList);
	}

	@Override
	public void delete(Long id) {
		exchangeListRepository.deleteById(id);
	}

}

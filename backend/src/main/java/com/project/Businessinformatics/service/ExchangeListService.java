package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.ExchangeList;

public interface ExchangeListService {

	public ArrayList<ExchangeList> getAll();
	
	public void save(ExchangeList exchangeList);
	
	public void delete(Long id);
	

	
}

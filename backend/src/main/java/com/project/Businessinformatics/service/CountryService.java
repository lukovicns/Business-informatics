package com.project.Businessinformatics.service;

import java.util.List;

import com.project.Businessinformatics.model.Country;


public interface CountryService {
	
	List<Country> getAll();
	public void save(Country country);
	public void delete(Country country);
	public void delete(Long id);
	Country getCountry(Long id);
}

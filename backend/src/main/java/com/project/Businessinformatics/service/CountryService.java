package com.project.Businessinformatics.service;

import java.util.ArrayList;
import com.project.Businessinformatics.model.Country;


public interface CountryService {
	
	ArrayList<Country> getAll();
	public void save(Country country);
	public void delete(Country country);
	public void delete(Long id);
	Country getCountry(Long id);
}

package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.Country;
import com.project.Businessinformatics.service.CountryService;
import com.project.Businessinformatics.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public ArrayList<Country> getAll() {
		return (ArrayList<Country>) countryRepository.findAll();
	}

	@Override
	public void save(Country country) {
		countryRepository.save(country);
	}

	@Override
	public void delete(Country country) {
		countryRepository.delete(country);
	}

	@Override
	public void delete(Long id) {
		countryRepository.deleteById(id);
	}

	@Override
	public Country getCountry(Long id) {
		return countryRepository.getOne(id);
	}

}

package com.project.Businessinformatics.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.City;
import com.project.Businessinformatics.repository.CityRepository;
import com.project.Businessinformatics.service.CityService;
import com.project.Businessinformatics.service.CountryService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryService countryService;

	@Override
	@Transactional(readOnly = true)
	public Collection<City> getCities() {
		return cityRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public City getCity(Long id) {
		return cityRepository.getOne(id);
	}

	@Override
	public City updateCity(Long countryId, City city) {
		City temp = cityRepository.getOne(city.getId());
		temp.setCountry(countryService.getCountry(countryId));
		temp.setName(city.getName());
		temp.setPttNumber(city.getPttNumber());
		return cityRepository.save(temp);
	}

	@Override
	public void deleteCity(Long id) {
		cityRepository.deleteById(id);
	}

}

package com.project.Businessinformatics.service;

import java.util.Collection;

import com.project.Businessinformatics.model.City;

public interface CityService {

	Collection<City> getCities();
	City getCity(Long id);
	City updateCity(Long countryId, City city);
	void deleteCity(Long id);
	
}

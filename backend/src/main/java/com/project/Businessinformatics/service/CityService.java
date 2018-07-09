package com.project.Businessinformatics.service;

import java.util.List;

import com.project.Businessinformatics.model.City;

public interface CityService {

	List<City> getCities();
	City getCity(Long id);
	City updateCity(Long countryId, City city);
	void deleteCity(Long id);
	
}

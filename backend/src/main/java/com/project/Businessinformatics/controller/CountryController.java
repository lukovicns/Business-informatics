package com.project.Businessinformatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.Country;
import com.project.Businessinformatics.service.CountryService;

@RestController
@RequestMapping(value="api/countries")
public class CountryController {

	@Autowired
	private CountryService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Country>> getCountries() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getCountry(@PathVariable Long id) {
		Country country = service.getCountry(id);
		if (country == null) {
			return new ResponseEntity<>("Country not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(country, HttpStatus.OK);
	}
}

package com.project.Businessinformatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.City;
import com.project.Businessinformatics.service.CityService;

@RestController
@RequestMapping(value="api/cities")
public class CityController {

	@Autowired
	private CityService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<City>> getCities() {
		return new ResponseEntity<>(service.getCities(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getCity(@PathVariable Long id) {
		City city = service.getCity(id);
		if (city == null) {
			return new ResponseEntity<>("City not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(city, HttpStatus.OK);
	}
}

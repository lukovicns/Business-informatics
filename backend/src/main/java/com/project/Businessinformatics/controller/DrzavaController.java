package com.project.Businessinformatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.Drzava;
import com.project.Businessinformatics.service.DrzavaService;

@RestController
@RequestMapping(value="/api/drzave")
public class DrzavaController {

	@Autowired
	private DrzavaService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Drzava>> getDrzave() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getDrzava(@PathVariable Long id) {
		Drzava drzava = service.findOne(id);
		if (drzava == null) {
			return new ResponseEntity<>("Drzava not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(drzava, HttpStatus.OK);
	}
}

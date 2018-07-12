package com.project.Businessinformatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.Currency;
import com.project.Businessinformatics.service.CurrencyService;

@RestController
@RequestMapping("api/currencies")
public class CurrencyController {

	@Autowired
	private CurrencyService service;
	
	@GetMapping
	public ResponseEntity<List<Currency>> getCurrencies() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}
}

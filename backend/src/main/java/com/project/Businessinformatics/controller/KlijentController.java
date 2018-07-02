package com.project.Businessinformatics.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.Klijent;
import com.project.Businessinformatics.security.JwtGenerator;
import com.project.Businessinformatics.service.KlijentService;

@RestController
@RequestMapping(value="api/klijenti")
public class KlijentController {

	@Autowired
	private KlijentService service;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Klijent>> getKlijenti() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getKlijent(@PathVariable Long id) {
		Klijent klijent = service.findOne(id);
		if (klijent == null) {
			return new ResponseEntity<>("Klijent not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(klijent, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Object> loginKlijent(@RequestBody Klijent data) {
		if (data.getEmail() == null || data.getEmail() == "" ||
				data.getPassword() == null || data.getPassword() == "") {
			return new ResponseEntity<>("Email i lozinka su obavezni.", HttpStatus.FORBIDDEN);
		}
		
		Klijent klijent = service.findByEmail(data.getEmail());
		if (klijent == null) {
			return new ResponseEntity<>("Klijent nije pronadjen.", HttpStatus.FORBIDDEN);
		}
		
		String token = jwtGenerator.generate(klijent);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("token", token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

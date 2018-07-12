package com.project.Businessinformatics.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.user.Client;
import com.project.Businessinformatics.security.JwtGenerator;
import com.project.Businessinformatics.service.ClientService;
import com.project.Businessinformatics.service.UserService;
import com.project.Businessinformatics.util.UserUtils;

@RestController
@RequestMapping(value="api/clients")
public class ClientController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClientService service;
	
	@Autowired
	private JwtGenerator jwtGenerator;

	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Client>> getClients() {
		return new ResponseEntity<>(service.getAllClients(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getClient(@PathVariable Long id) {
		Client client = service.getClient(id);
		if (client == null) {
			return new ResponseEntity<>("Client not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Client data) {
		if (data.getEmail() == null || data.getEmail() == "" ||
			data.getPassword() == null || data.getPassword() == "") {
			return new ResponseEntity<>("Email and password are required.", HttpStatus.FORBIDDEN);
		}
		
		Client client = service.getClientByEmailAndPassword(data.getEmail(), data.getPassword());
		if (client == null) {
			return new ResponseEntity<>("Invalid credentials provided.", HttpStatus.FORBIDDEN);
		}
		
		String token = jwtGenerator.generateClient(client);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("token", token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody Client data) {
		if (data.getName() == null || data.getName() == "" || data.getSurname() == null || data.getSurname() == "" ||
			data.getEmail() == null || data.getEmail() == "" || data.getPassword() == null || data.getPassword() == "" ||
			data.getAddress() == null || data.getAddress() == "" || data.getDateOfBirth() == null || data.getDateOfBirth() == "") {
			return new ResponseEntity<>("All fields are required (name, surname, email, password, address, dateOfBirth).", HttpStatus.FORBIDDEN);
		}
		UserUtils userUtils = new UserUtils();
		
		Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
		Matcher emaildMatcher = emailPattern.matcher(data.getEmail());

		if (!emaildMatcher.find()) {
			return new ResponseEntity<>("Email is in wrong format.", HttpStatus.FORBIDDEN);
		}
		
		if (userUtils.userExists(data.getEmail(), userService, service)) {
			return new ResponseEntity<>("Another user with this email already exists.", HttpStatus.FORBIDDEN);
		}
		
		if (data.getPassword().length() < 8) {
			return new ResponseEntity<>("Password must be at least 8 characters long.", HttpStatus.FORBIDDEN);
		}
		
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			dateFormatter.setLenient(false);
			Date dateOfBirth = dateFormatter.parse(data.getDateOfBirth());
			
			Client client = new Client(data.getName(), data.getSurname(), data.getEmail(), data.getPassword(), data.getAddress(), dateFormatter.format(dateOfBirth));
			return new ResponseEntity<>(service.save(client), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("You must provide a valid date (format: yyyy-MM-dd).", HttpStatus.FORBIDDEN);
		}
	}
}

package com.project.Businessinformatics.controller;

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

import com.project.Businessinformatics.model.user.User;
import com.project.Businessinformatics.security.JwtGenerator;
import com.project.Businessinformatics.service.ClientService;
import com.project.Businessinformatics.service.UserService;
import com.project.Businessinformatics.util.UserUtils;

@RestController
@RequestMapping(value="api/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable Long id) {
		User user = service.getUser(id);
		if (user == null) {
			return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody User data) {
		if (data.getEmail() == null || data.getEmail() == "" ||
				data.getPassword() == null || data.getPassword() == "") {
			return new ResponseEntity<>("Email and password are required.", HttpStatus.FORBIDDEN);
		}
		
		User user = service.getUserByEmailAndPassword(data.getEmail(), data.getPassword());
		if (user == null) {
			return new ResponseEntity<>("Invalid credentials provided.", HttpStatus.FORBIDDEN);
		}
		
		String token = jwtGenerator.generateUser(user);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("token", token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody User data) {
		if (data.getName() == null || data.getName() == "" || data.getSurname() == null || data.getSurname() == "" ||
			data.getEmail() == null || data.getEmail() == "" || data.getPassword() == null || data.getPassword() == "") {
			return new ResponseEntity<>("All fields are required (name, surname, email, password).", HttpStatus.FORBIDDEN);
		}
		UserUtils userUtils = new UserUtils();
		
		Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
		Matcher emaildMatcher = emailPattern.matcher(data.getEmail());

		if (!emaildMatcher.find()) {
			return new ResponseEntity<>("Email is in wrong format.", HttpStatus.FORBIDDEN);
		}
		
		if (userUtils.userExists(data.getEmail(), service, clientService)) {
			return new ResponseEntity<>("Another user with this email already exists.", HttpStatus.FORBIDDEN);
		}
		
		if (data.getPassword().length() < 8) {
			return new ResponseEntity<>("Password must be at least 8 characters long.", HttpStatus.FORBIDDEN);
		}
		
		if (data.getPassword().length() < 8) {
			return new ResponseEntity<>("Password must be at least 8 characters long.", HttpStatus.FORBIDDEN);
		}
		
		User user = new User(data.getName(), data.getSurname(), data.getEmail(), data.getPassword());
		return new ResponseEntity<>(service.createUser(user), HttpStatus.OK);
	}
}

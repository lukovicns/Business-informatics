package com.project.Businessinformatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.xml.RTGSRequest;
import com.project.Businessinformatics.service.RTGSRequestService;

@RestController
@RequestMapping("api/rtgs")
public class RTGSRequestController {

	@Autowired
	private RTGSRequestService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
	public ResponseEntity<List<RTGSRequest>> getRTGSRequests() {
		return new ResponseEntity<>(service.getAllRTGSRequests(), HttpStatus.OK);
	}	
}

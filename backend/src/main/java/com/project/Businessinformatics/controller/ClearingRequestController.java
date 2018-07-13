package com.project.Businessinformatics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;
import com.project.Businessinformatics.service.ClearingSettlementRequestService;

@RestController
@RequestMapping("api/clearings")
public class ClearingRequestController {

	@Autowired
	private ClearingSettlementRequestService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
	public ResponseEntity<List<ClearingSettlementRequest>> getClearingSettlementRequests() {
		return new ResponseEntity<>(service.getAllClearingSettlementRequests(), HttpStatus.OK);
	}
}

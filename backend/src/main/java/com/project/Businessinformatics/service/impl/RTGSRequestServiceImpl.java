package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.xml.RTGSRequest;
import com.project.Businessinformatics.repository.RTGSRequestRepository;
import com.project.Businessinformatics.service.RTGSRequestService;

@Service
public class RTGSRequestServiceImpl implements RTGSRequestService {
	
	@Autowired
	private RTGSRequestRepository rTGSRequestRepository;

	@Override
	public void save(RTGSRequest req) {
		rTGSRequestRepository.save(req);
	}

	@Override
	public ArrayList<RTGSRequest> getAllRTGSRequests() {
		return (ArrayList<RTGSRequest>) rTGSRequestRepository.findAll();
	}

}

package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.xml.RTGSRequest;

public interface RTGSRequestService {

	public void save(RTGSRequest req);
	
	public ArrayList<RTGSRequest> getAllRTGSRequests();
}

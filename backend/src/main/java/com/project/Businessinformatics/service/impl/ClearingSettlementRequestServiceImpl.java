package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;
import com.project.Businessinformatics.repository.ClearingSettlementRequestRepository;
import com.project.Businessinformatics.service.ClearingSettlementRequestService;

@Service
public class ClearingSettlementRequestServiceImpl implements ClearingSettlementRequestService {
	
	@Autowired
	private ClearingSettlementRequestRepository clearingSettlementRequestRepository;

	@Override
	public void save(ClearingSettlementRequest csr) {
		clearingSettlementRequestRepository.save(csr);
	}

	@Override
	public ArrayList<ClearingSettlementRequest> getAllClearingSettlementRequests() {
		return (ArrayList<ClearingSettlementRequest>) clearingSettlementRequestRepository.findAll();
	}

}

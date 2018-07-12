package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;

public interface ClearingSettlementRequestService {

	void save(ClearingSettlementRequest csr);
	ArrayList<ClearingSettlementRequest> getAllClearingSettlementRequests();
	ClearingSettlementRequest search(Long id, Long id2);

}

package com.project.Businessinformatics.service.impl;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.repository.AnalyticalStatementRepository;
import com.project.Businessinformatics.service.AnaltyicalStatementService;

@Service
@Transactional
public class AnaltyicalStatementServiceImpl implements AnaltyicalStatementService {
	

	@Autowired
	private AnalyticalStatementRepository analyticalStatementRepository;

	@Override
	@Transactional(readOnly = true)
	public Collection<AnalyticalStatement> getAnalyticalStatements() {
		return analyticalStatementRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public AnalyticalStatement getAnalyticalStatement(Long id) {
		return analyticalStatementRepository.getOne(id);
	}
	
	


}

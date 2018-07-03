package com.project.Businessinformatics.service;

import java.util.Collection;

import com.project.Businessinformatics.model.AnalyticalStatement;


public interface AnaltyicalStatementService {

	Collection<AnalyticalStatement> getAnalyticalStatements();
	AnalyticalStatement getAnalyticalStatement(Long id);
	
}

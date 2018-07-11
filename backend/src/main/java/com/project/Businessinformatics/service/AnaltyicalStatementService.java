package com.project.Businessinformatics.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.project.Businessinformatics.model.AnalyticalStatement;

import net.sf.jasperreports.engine.JRException;


public interface AnaltyicalStatementService {

	Collection<AnalyticalStatement> getAnalyticalStatements();
	AnalyticalStatement getAnalyticalStatement(Long id);
	
	Collection<AnalyticalStatement> createAnalyticalStatement(String currencyId, 
			  String cityId,
			  Date dateOfReceipt,
			  Date currencyDate,
			  AnalyticalStatement analyticalStatement);
	
	Collection<AnalyticalStatement> doTransaction(AnalyticalStatement analyticalStatement);
	
	Collection<AnalyticalStatement> updateAnalyticalStatement(String currencyId,
			  String cityId,
			  Date dateOfReceipt,
			  Date currencyDate,
			  AnalyticalStatement analyticalStatement);

	Collection<AnalyticalStatement> deleteAnalyticalStatement(Long id);

	Collection<AnalyticalStatement> getAnalyticalStatementsByDailyAccountStatusId(Long id);
	
	Collection<AnalyticalStatement> searchAnalyticalStatements(Long currencyId,
			   Long cityId, 
			   Long dailyAccountStatusId,
			   Date dateOfReceipt,
			   Date currencyDate,
			   AnalyticalStatement analyticalStatement);
	
	void exportToPdf(Long accountId,Date startDate,Date endDate,HttpServletResponse response) throws JRException, SQLException, IOException;
	
	void exportToXml(Long accountId,Date startDate,Date endDate,HttpServletResponse response) throws JRException, SQLException, IOException;
	
}

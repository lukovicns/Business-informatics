package com.project.Businessinformatics.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import com.project.Businessinformatics.model.AnalyticalStatement;

import net.sf.jasperreports.engine.JRException;

public interface AnaltyicalStatementService {

	Collection<AnalyticalStatement> getAnalyticalStatements();
	AnalyticalStatement getAnalyticalStatement(Long id);
	
	Collection<AnalyticalStatement> createAnalyticalStatement(String currencyId, 
			  String cityId,
			  String dateOfReceipt,
			  String currencyDate,
			  AnalyticalStatement analyticalStatement) throws DatatypeConfigurationException, JAXBException;
	
	Collection<AnalyticalStatement> doTransaction(AnalyticalStatement analyticalStatement) throws JAXBException, DatatypeConfigurationException;

	void exportToPdf(Long accountId,Date startDate,Date endDate,HttpServletResponse response) throws  SQLException, IOException, JRException;
	
	void exportToXml(Long accountId,Date startDate,Date endDate,HttpServletResponse response) throws  SQLException, IOException, JRException;
	
}

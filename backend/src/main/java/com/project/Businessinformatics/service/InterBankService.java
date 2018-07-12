package com.project.Businessinformatics.service;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeConfigurationException;

import com.project.Businessinformatics.model.AnalyticalStatement;

public interface InterBankService {

	public void generateRTGSRequest(AnalyticalStatement as) throws DatatypeConfigurationException;
	
	public void addToClearing(AnalyticalStatement as) throws JAXBException;
	
	public void RTGSOrClearing(AnalyticalStatement as) throws DatatypeConfigurationException, JAXBException;

	void generateClearingService() throws PropertyException, JAXBException;

	void generateLastClearingService() throws JAXBException;
}

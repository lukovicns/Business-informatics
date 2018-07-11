package com.project.Businessinformatics.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.project.Businessinformatics.model.Bank;

import net.sf.jasperreports.engine.JRException;

public interface BankService {
	
	public ArrayList<Bank> getAllBanks();
	public Bank createBank(Bank b,Long countryId);
	public Bank updateBank(Bank b,Long countryId);
	public Bank deleteBank(Long id);
	public ArrayList<Bank> searchBanks(Bank b, Long countryId);
	public void exportToPdf(Long bankId,HttpServletResponse response) throws JRException, IOException, SQLException;
	
}

package com.project.Businessinformatics.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.Bank;
import com.project.Businessinformatics.model.Country;
import com.project.Businessinformatics.repository.BankRepository;
import com.project.Businessinformatics.repository.CountryRepository;
import com.project.Businessinformatics.service.BankService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
    @Autowired
    DataSource dataSource;
	
	@Override
	public Bank createBank(Bank b,Long countryId) {
		Country c = countryRepository.getOne(countryId);
		b.setCountry(c);
		Bank bank = bankRepository.save(b);
		return bank;
	}

	@Override
	public Bank updateBank(Bank b,Long countryId) {
		Bank bank = bankRepository.getOne(b.getId());
		bank.setAddress(b.getAddress());
		bank.setBanka(b.isBanka());
		bank.setEmail(b.getEmail());
		bank.setFax(b.getFax());
		bank.setName(b.getName());
		bank.setPib(b.getPib());
		bank.setSwift(b.getSwift());
		bank.setTelephone(b.getTelephone());
		bank.setTransactionAccount(b.getTransactionAccount());
		bank.setWeb(b.getWeb());
		Country c = countryRepository.getOne(countryId);
		bank.setCountry(c);
		return bankRepository.save(bank);
	}

	@Override
	public Bank deleteBank(Long id) {
		Bank b = bankRepository.getOne(id);
		if(b != null) {
			bankRepository.delete(b);
			return b;
		} 
		else {
			return null;
		}
	}

	@Override
	public ArrayList<Bank> getAllBanks() {
		ArrayList<Bank> banks = (ArrayList<Bank>) bankRepository.findAll();
		if(banks != null){
			return banks;
		}
		return null;
	}
	
	@Override
	public ArrayList<Bank> searchBanks(Bank b, Long countryId) {
		
		Country c = countryRepository.getOne(countryId);
		
		String name = "";
		String pib = "";
		String address = "";
		String email = "";
		String web = "";
		String telephone = "";
		String fax = "";
		String swift = "";
		String transactionAccount = "";
		String countryName = "";
		
		if(b.getName() != null)
			name = "%" + b.getName() + "%";
		
		if(b.getPib() != null)
			pib = "%" + b.getPib() + "%";
		
		if(b.getAddress() != null)
			address = "%" + b.getAddress() + "%";
		
		if(b.getEmail() != null)
			email = "%" + b.getEmail() + "%";
		
		if(b.getWeb() != null)
			web = "%" + b.getWeb() + "%";

		if(b.getTelephone() != null)
			telephone = "%" + b.getTelephone() + "%";
		
		if(b.getFax() != null)
			fax = "%" + b.getFax() + "%";
		
		if(b.getSwift() != null)
			swift = "%" + b.getSwift() + "%";
		
		if(b.getTransactionAccount() != null)
			transactionAccount = "%" + b.getTransactionAccount() + "%";
		
		if(c != null){
			countryName = c.getName();
		}
		
	
		return bankRepository.searchBanks(name,pib,address,email,web,telephone,fax,swift,transactionAccount,countryName);
		
	}
	
	@Override
	public Bank findBankByLeadNumber(String substring) {
		return bankRepository.findBankByLeadNumber(substring);
	}

	@Override
	public void exportToPdf(Long bankId,HttpServletResponse response) throws JRException, IOException, SQLException {
		Bank b = bankRepository.getOne(bankId);
	    Map<String,Object> params = new HashMap<>();
	    System.out.println("bank "+b.getSwift());
	    params.put("bank", b.getSwift());
	    FileInputStream fileInputStream;
	    System.out.println("datasource "+dataSource.getConnection().toString());
		JasperPrint jp  = JasperFillManager.fillReport(getClass().getResource("/jasper/BankAccountReport.jasper").openStream(),params, dataSource.getConnection());
		File pdf = new File(
				System.getProperty("user.home") + "/Downloads/" + "izvestajBanka-" + bankId + ".pdf");
		FileOutputStream out = new FileOutputStream(pdf);
		JasperExportManager.exportReportToPdfStream(jp, out);
		fileInputStream = new FileInputStream(pdf);
		IOUtils.copy(fileInputStream, response.getOutputStream());
		fileInputStream.close();
		out.close();
		response.flushBuffer();
		
	}

	public Bank getBank(Long id) {
		return bankRepository.getOne(id);
	}

}

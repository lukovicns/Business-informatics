package com.project.Businessinformatics.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.model.AnalyticalStatementMode;
import com.project.Businessinformatics.model.DailyAccountStatus;
import com.project.Businessinformatics.model.Account;
import com.project.Businessinformatics.repository.AccountRepository;
import com.project.Businessinformatics.repository.CurrencyExchangeRepository;
import com.project.Businessinformatics.service.AccountService;
import com.project.Businessinformatics.service.AnaltyicalStatementService;
import com.project.Businessinformatics.service.DailyAccountStatusService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@Autowired
	private DailyAccountStatusService dailyAccountStatusService;
	
	@Autowired
	private AnaltyicalStatementService analyticalStatementService;
	
	@Override
	public Account createAccount(Account a) {
		return accountRepository.save(a);
	}

	@Override
	public Account updateAccount(Account a) {
		Account account = accountRepository.getOne(a.getId());
		if(account != null){
			account.setAccountNumber(a.getAccountNumber());
			account.setActive(a.isActive());
			account.setBank(a.getBank());
			account.setClient(a.getClient());
			account.setCurrency(a.getCurrency());
			account.setOpeningDate(a.getOpeningDate());
			return accountRepository.save(a);
		} else {
			return null;
		}
	}

	@Override
	public Account deleteAccount(Long id) {
		Account account = accountRepository.getOne(id);
		if(account != null){
			account.setActive(false);
			return accountRepository.save(account);
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<Account> getAllAccounts() {
		ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findAll();
		if(accounts != null){
			return accounts;
		} else {
			return null;
		}
	}

	@Override
	public Account getAccount(Long id) {
		return accountRepository.getOne(id);
	}
	
	@Override
	public ArrayList<Account> getAllAccountsForBank(Long bankId) {
		return accountRepository.findAccountsByBankIdOrderByOpeningDateAsc(bankId);
	}
	
	
	
	@Override
	public Collection<Account> search(String accountNumber, Date openingMin, Date openingMax, String bankName,
			String name, String surname, String currency) {
		return accountRepository.search(accountNumber, openingMin, openingMax, bankName, name, surname, currency);
	}
	
	@Override
	public Collection<Account> searchWithActive(String accountNumber, Date openingMin, Date openingMax, String bankName,
			String name, String surname, String currency, boolean active) {
		return accountRepository.searchWithActive(accountNumber, openingMin, openingMax, bankName, name, surname, currency, active);
	}
	
	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		return accountRepository.findAccountByAccountNumber(accountNumber);
	}
	
	@Override
	public void transferAccount(Account account, String accountNumber) throws JAXBException, DatatypeConfigurationException, ParseException {
		
		Account recipient = accountRepository.findAccountByAccountNumber(accountNumber);
		AnalyticalStatement transferStatement = new AnalyticalStatement();
		DailyAccountStatus originatorAccountAmmount = dailyAccountStatusService.getLastDailyAccountStatus(account.getAccountNumber());
		Date currencyDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(currencyDate);
		transferStatement.setOriginator(account.getClient().getName() + " " + account.getClient().getSurname());
		transferStatement.setPurpose("Prenos novca ukinutog racuna");
		transferStatement.setRecipient(recipient.getClient().getName() + " " + recipient.getClient().getSurname());
		transferStatement.setDateOfReceipt(date);
		transferStatement.setCurrencyDate(date);
		transferStatement.setOriginatorAccount(account.getAccountNumber());
		transferStatement.setRecipientAccount(recipient.getAccountNumber());
		transferStatement.setCurrency(account.getCurrency());
		transferStatement.setUrgently(false);
		transferStatement.setPayment(true);
		transferStatement.setAnalyticalStatementMode(AnalyticalStatementMode.TRANSFER);
		
		double exchangeRate;
		if(!account.getCurrency().getOfficialCode().equals(recipient.getCurrency().getOfficialCode())) {
			exchangeRate = currencyExchangeRepository.findMiddleRate(account.getCurrency().getOfficialCode(), recipient.getCurrency().getOfficialCode());
		} else {
			exchangeRate = 1;
		}
		double ammount = originatorAccountAmmount.getCurrentAmount();
		transferStatement.setAmount(ammount / exchangeRate);
		
	    analyticalStatementService.doTransaction(transferStatement);
	}

}

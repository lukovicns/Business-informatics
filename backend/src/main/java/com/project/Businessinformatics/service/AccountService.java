package com.project.Businessinformatics.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.project.Businessinformatics.model.Account;

public interface AccountService {
	
	public ArrayList<Account> getAllAccounts();
	public ArrayList<Account> getAllAccountsForBank(Long bankId);
	public Account createAccount(Account a);
	public Account updateAccount(Account a);
	public Account deleteAccount(Long id);
	Account getAccount(Long id);
	public Collection<Account> search(String accountNumber, Date openingMin, Date openingMax, String bankName, String name, String surname, String currency);
	public Collection<Account> searchWithActive(String accountNumber, Date openingMin, Date openingMax, String bankName, String name, String surname, String currency, boolean active);
	


}

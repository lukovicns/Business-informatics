package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.Account;

public interface AccountService {
	
	public ArrayList<Account> getAllAccounts();
	public Account createAccount(Account a);
	public Account updateAccount(Account a);
	public Account deleteAccount(Long id);
	Account getAccount(Long id);

}

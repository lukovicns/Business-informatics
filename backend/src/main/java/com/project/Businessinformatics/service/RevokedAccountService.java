package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.Account;
import com.project.Businessinformatics.model.RevokedAccount;

public interface RevokedAccountService {
	
	public RevokedAccount createRevokedAccount(RevokedAccount ra);
	public RevokedAccount createRevokedAccount(Account a,String transverAcc);
	public ArrayList<RevokedAccount> getAllRevokedAccounts();
	public RevokedAccount updateRevokedAccount(RevokedAccount ra);
	public RevokedAccount deleteRevokedAccount(Long id);
}

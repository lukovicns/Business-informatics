package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.RevokedAccount;

public interface RevokedAccountService {
	
	public ArrayList<RevokedAccount> getAllRevokedAccounts();
	public RevokedAccount updateRevokedAccount(RevokedAccount ra);
	public RevokedAccount deleteRevokedAccount(Long id);
}

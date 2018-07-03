package com.project.Businessinformatics.service;

import java.util.ArrayList;

import com.project.Businessinformatics.model.Bank;

public interface BankService {
	
	public ArrayList<Bank> getAllBanks();
	public Bank createBank(Bank b,Long countryId);
	public Bank updateBank(Bank b,Long countryId);
	public Bank deleteBank(Long id);
	
}

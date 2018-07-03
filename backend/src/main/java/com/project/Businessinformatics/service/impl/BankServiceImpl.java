package com.project.Businessinformatics.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.Bank;
import com.project.Businessinformatics.model.Country;
import com.project.Businessinformatics.repository.BankRepository;
import com.project.Businessinformatics.repository.CountryRepository;
import com.project.Businessinformatics.service.BankService;

@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
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

}

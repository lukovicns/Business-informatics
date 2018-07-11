package com.project.Businessinformatics.service.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.Account;
import com.project.Businessinformatics.model.DailyAccountStatus;
import com.project.Businessinformatics.repository.DailyAccountStatusRepository;
import com.project.Businessinformatics.service.AccountService;
import com.project.Businessinformatics.service.DailyAccountStatusService;

@Service
@Transactional
public class DailyAccountStatusServiceImpl implements DailyAccountStatusService{

	@Autowired
	private DailyAccountStatusRepository dailyAccountStatusRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public DailyAccountStatus createDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date) {
		Account account = accountService.getAccount(accountId);
		dailyAccountStatus.setAccount(account);
		dailyAccountStatus.setDate(date);
		return dailyAccountStatusRepository.save(dailyAccountStatus);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<DailyAccountStatus> getDailyAccountStatuses() {
		return dailyAccountStatusRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<DailyAccountStatus> searchDailyAccountStatuses(Long accountId, DailyAccountStatus dailyAccountStatus, Date date) {
		String accountNumber = "";
		if(accountId != null && accountId >= 0)
			accountNumber = accountService.getAccount(accountId).getAccountNumber();
		Double previousAmount = new Double(Double.MAX_VALUE);
		if(dailyAccountStatus.getPreviousAmount() > 0){
			previousAmount = dailyAccountStatus.getPreviousAmount();
		}
		Double transferInFavor = new Double(Double.MAX_VALUE);
		if(dailyAccountStatus.getTransferInFavor() > 0){
			transferInFavor = dailyAccountStatus.getTransferInFavor();
		}
		Integer numberOfChanges = new Integer(Integer.MAX_VALUE);
		if(dailyAccountStatus.getNumberOfChanges() > 0){
			numberOfChanges = dailyAccountStatus.getNumberOfChanges();
		}
		Double transferExpenses = new Double(Double.MAX_VALUE);
		if(dailyAccountStatus.getTransferExpenses() > 0){
			transferExpenses = dailyAccountStatus.getTransferExpenses();
		}
		Double currentAmount = new Double(Double.MAX_VALUE);
		if(dailyAccountStatus.getCurrentAmount() > 0){
			currentAmount = dailyAccountStatus.getCurrentAmount();
		}
		if(date == null){
			date = new Date(Long.MAX_VALUE);
		}
		return dailyAccountStatusRepository.searchDailyAccountStatuses(accountNumber, previousAmount, transferInFavor, numberOfChanges, transferExpenses, currentAmount, new Date(Long.MIN_VALUE), date);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public DailyAccountStatus getDailyAccountStatus(Long id) {
		return dailyAccountStatusRepository.getOne(id);
	}

	@Override
	public void deleteDailyAccountStatus(Long id) {
		dailyAccountStatusRepository.deleteById(id);
	}
	
	@Override
	public DailyAccountStatus updateDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date) {
		Account account = accountService.getAccount(accountId);
		DailyAccountStatus temp = dailyAccountStatusRepository.findOne(dailyAccountStatus.getId());
		temp.setAccount(account);
		temp.setCurrentAmount(dailyAccountStatus.getCurrentAmount());
		temp.setDate(dailyAccountStatus.getDate());
		temp.setNumberOfChanges(dailyAccountStatus.getNumberOfChanges());
		temp.setPreviousAmount(dailyAccountStatus.getPreviousAmount());
		temp.setTransferExpenses(dailyAccountStatus.getTransferExpenses());
		temp.setTransferInFavor(dailyAccountStatus.getTransferInFavor());
		temp.setDate(date);
		return dailyAccountStatusRepository.save(temp);
	}
}

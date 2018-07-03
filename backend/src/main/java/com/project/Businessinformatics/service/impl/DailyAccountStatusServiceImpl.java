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
	public DailyAccountStatus getDailyAccountStatus(Long id) {
		return dailyAccountStatusRepository.getOne(id);
	}

	@Override
	public void deleteDailyAccountStatus(Long id) {
		dailyAccountStatusRepository.deleteById(id);
	}
}

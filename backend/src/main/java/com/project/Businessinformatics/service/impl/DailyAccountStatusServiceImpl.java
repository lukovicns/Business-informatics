package com.project.Businessinformatics.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.Account;
import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.model.DailyAccountStatus;
import com.project.Businessinformatics.repository.DailyAccountStatusRepository;
import com.project.Businessinformatics.service.AccountService;
import com.project.Businessinformatics.service.DailyAccountStatusService;


@Service
@Transactional
public class DailyAccountStatusServiceImpl implements DailyAccountStatusService{

	private final DailyAccountStatusRepository dailyAccountStatusRepository;
	private final AccountService accountService;
	
	@Autowired
	public DailyAccountStatusServiceImpl(DailyAccountStatusRepository dailyAccountStatusRepository,
										 AccountService accountService){
		this.dailyAccountStatusRepository = dailyAccountStatusRepository;
		this.accountService = accountService;
	}

	@Override
	public DailyAccountStatus createDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date) {
		Account account = accountService.getAccount(accountId);
		dailyAccountStatus.setAccount(account);
		dailyAccountStatus.setDate(date.toString());
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
	public DailyAccountStatus updateDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date) {
		Account account = accountService.getAccount(accountId);
		DailyAccountStatus temp = dailyAccountStatusRepository.getOne(dailyAccountStatus.getId());
		temp.setAccount(account);
		temp.setCurrentAmount(dailyAccountStatus.getCurrentAmount());
		temp.setDate(dailyAccountStatus.getDate());
		temp.setNumberOfChanges(dailyAccountStatus.getNumberOfChanges());
		temp.setPreviousAmount(dailyAccountStatus.getPreviousAmount());
		temp.setTransferExpenses(dailyAccountStatus.getTransferExpenses());
		temp.setTransferInFavor(dailyAccountStatus.getTransferInFavor());
		temp.setDate(date.toString());
		return dailyAccountStatusRepository.save(temp);
	}

	@Override
	public void deleteDailyAccountStatus(Long id) {
		dailyAccountStatusRepository.deleteById(id);
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
	
	public DailyAccountStatus updateOriginatorDailyAccountStatus(AnalyticalStatement analyticalStatement) throws ParseException{
		DailyAccountStatus dailyAccountStatus = this.getLastDailyAccountStatus(analyticalStatement.getOriginatorAccount());
		dailyAccountStatus.setNumberOfChanges(dailyAccountStatus.getNumberOfChanges() + 1);
		dailyAccountStatus.setPreviousAmount(dailyAccountStatus.getCurrentAmount());
		dailyAccountStatus.setTransferExpenses(dailyAccountStatus.getTransferExpenses() + analyticalStatement.getAmount());
		dailyAccountStatus.setCurrentAmount(dailyAccountStatus.getCurrentAmount() - analyticalStatement.getAmount());
		return dailyAccountStatusRepository.save(dailyAccountStatus);
	}
	
	public DailyAccountStatus updateRecipiantDailyAccountStatus(AnalyticalStatement analyticalStatement) throws ParseException{
		DailyAccountStatus dailyAccountStatus = this.getLastDailyAccountStatus(analyticalStatement.getRecipientAccount());
		dailyAccountStatus.setNumberOfChanges(dailyAccountStatus.getNumberOfChanges() + 1);
		dailyAccountStatus.setPreviousAmount(dailyAccountStatus.getCurrentAmount());
		dailyAccountStatus.setTransferInFavor(dailyAccountStatus.getTransferInFavor() + analyticalStatement.getAmount());
		dailyAccountStatus.setCurrentAmount(dailyAccountStatus.getCurrentAmount() + analyticalStatement.getAmount());
		return dailyAccountStatusRepository.save(dailyAccountStatus);
	}
	
	@SuppressWarnings("unused")
	public DailyAccountStatus getLastDailyAccountStatus(String accountNumber) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date endDate = calendar.getTime();
		DailyAccountStatus dailyAccountStatus = dailyAccountStatusRepository.findDailyAccountStatusByLastDateAndAccountNumber(accountNumber);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    String date = dateFormatter.format(new Date());
	    Date dailyAccountStatusDate = dateFormatter.parse(dailyAccountStatus.getDate());
		if(dailyAccountStatus == null) {
			dailyAccountStatus = new DailyAccountStatus();
			dailyAccountStatus.setAccount(accountService.getAccountByAccountNumber(accountNumber));
			dailyAccountStatus.setDate(date);
		}else if(dailyAccountStatusDate.before(startDate) || dailyAccountStatusDate.after(endDate)){
			DailyAccountStatus newDailyAccountStatus = new DailyAccountStatus();
			newDailyAccountStatus.setAccount(dailyAccountStatus.getAccount());
			newDailyAccountStatus.setDate(date);
			newDailyAccountStatus.setCurrentAmount(dailyAccountStatus.getCurrentAmount());
			newDailyAccountStatus.setPreviousAmount(dailyAccountStatus.getPreviousAmount());
			return newDailyAccountStatus;
		}
		return dailyAccountStatus;
	}

	@Override
	public DailyAccountStatus getDailyAccountStatusForAccount(Long accountId, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date endDate = calendar.getTime();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    String sDate = dateFormatter.format(startDate);
	    String eDate = dateFormatter.format(endDate);
		return this.dailyAccountStatusRepository.findDailyAccountStatusByAccountAndDate(accountId, sDate, eDate);
	}
	
}

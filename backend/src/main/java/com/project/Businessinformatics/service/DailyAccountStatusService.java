package com.project.Businessinformatics.service;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.model.DailyAccountStatus;


public interface DailyAccountStatusService {

	DailyAccountStatus createDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date);
	Collection<DailyAccountStatus> getDailyAccountStatuses();
	DailyAccountStatus getDailyAccountStatus(Long id);
	DailyAccountStatus updateDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date);
	void deleteDailyAccountStatus(Long id);
	Collection<DailyAccountStatus> searchDailyAccountStatuses(Long accountId, DailyAccountStatus dailyAccountStatus, Date date);
	DailyAccountStatus updateOriginatorDailyAccountStatus(AnalyticalStatement analyticalStatement) throws ParseException;
	DailyAccountStatus updateRecipiantDailyAccountStatus(AnalyticalStatement analyticalStatement) throws ParseException;
	DailyAccountStatus getLastDailyAccountStatus(String accountNumber) throws ParseException;
	DailyAccountStatus getDailyAccountStatusForAccount(Long accountId, Date date);
	
}

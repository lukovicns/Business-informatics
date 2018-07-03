package com.project.Businessinformatics.service;

import java.util.Collection;
import java.util.Date;

import com.project.Businessinformatics.model.DailyAccountStatus;

public interface DailyAccountStatusService {

	DailyAccountStatus createDailyAccountStatus(Long accountId, DailyAccountStatus dailyAccountStatus, Date date);
	Collection<DailyAccountStatus> getDailyAccountStatuses();
	DailyAccountStatus getDailyAccountStatus(Long id);
	void deleteDailyAccountStatus(Long id);
	
}

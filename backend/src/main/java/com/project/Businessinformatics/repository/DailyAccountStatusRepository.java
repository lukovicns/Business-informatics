package com.project.Businessinformatics.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.DailyAccountStatus;

@Repository
public interface DailyAccountStatusRepository extends JpaRepository<DailyAccountStatus, Long> {

	DailyAccountStatus findOne(Long id);
	
	@Query("select status from DailyAccountStatus status inner join status.account as account where account.accountNumber like %?1% and status.previousAmount <= ?2 and status.transferInFavor <= ?3 and status.numberOfChanges <= ?4 and status.transferExpenses <= ?5 and status.currentAmount <= ?6 and status.date between ?7 and ?8")
	Collection<DailyAccountStatus> searchDailyAccountStatuses(String accountNumber, Double previousAmount, Double transferInFavor, Integer numberOfChanges, Double transferExpenses, Double currentAmount, java.util.Date date, java.util.Date date2);
	
}

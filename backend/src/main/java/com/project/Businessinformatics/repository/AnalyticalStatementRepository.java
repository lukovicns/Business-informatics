package com.project.Businessinformatics.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Businessinformatics.model.AnalyticalStatement;

public interface AnalyticalStatementRepository extends JpaRepository<AnalyticalStatement, Long> {

	AnalyticalStatement findOne(Long id);
	
	Collection<AnalyticalStatement> findAnalyticalStatementsByDailyAccountStatusId(Long id);
	
	Collection<AnalyticalStatement> searchAnalyticalStatements(String currencyCode, 
		    String cityPttNumber, String accountNumber,
			String originator, String purpose, String recipient, Date minimumDate, Date dateOfReceipt,
			Date currencyDate, String originatorAccount, String model, String debitAuthorizationNumber,
			String recipientAccount, String approvalModel, String approvalAuthorizationNumber,
			boolean urgently, Double amount);
	
	@Query("select stat from AnalyticalStatement as stat where (stat.originatorAccount=?1 or stat.recipientAccount=?1) and stat.dateOfReceipt between ?2 and ?3")
	List<AnalyticalStatement> findClientStatements(String accountNumber, Date startDate, Date endDate);
	

}

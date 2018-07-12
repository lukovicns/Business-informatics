package com.project.Businessinformatics.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Businessinformatics.model.AnalyticalStatement;

public interface AnalyticalStatementRepository extends JpaRepository<AnalyticalStatement, Long> {

//	@Query("select stat from AnalyticalStatement as stat where (stat.originatorAccount=?1 or stat.recipientAccount=?1) and stat.dateOfReceipt between ?2 and ?3")
//	List<AnalyticalStatement> findClientStatements(String accountNumber, Date startDate, Date endDate);
	

}

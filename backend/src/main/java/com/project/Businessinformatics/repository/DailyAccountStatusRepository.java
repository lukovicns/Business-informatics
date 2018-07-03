package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.DailyAccountStatus;

@Repository
public interface DailyAccountStatusRepository extends JpaRepository<DailyAccountStatus, Long> {
	
}

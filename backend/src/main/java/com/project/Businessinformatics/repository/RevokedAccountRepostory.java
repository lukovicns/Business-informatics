package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.RevokedAccount;

@Repository
public interface RevokedAccountRepostory extends JpaRepository<RevokedAccount, Long> {
	
}

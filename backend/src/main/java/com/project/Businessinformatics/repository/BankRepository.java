package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long>{

}

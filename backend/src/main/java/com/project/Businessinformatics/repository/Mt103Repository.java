package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Businessinformatics.model.xml.Mt103Request;

public interface Mt103Repository extends JpaRepository<Mt103Request, Long>{
	
}

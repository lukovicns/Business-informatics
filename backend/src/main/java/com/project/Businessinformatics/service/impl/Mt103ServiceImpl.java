package com.project.Businessinformatics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.xml.Mt103Request;
import com.project.Businessinformatics.repository.Mt103Repository;
import com.project.Businessinformatics.service.Mt103Service;

@Service
public class Mt103ServiceImpl implements Mt103Service{

	private final Mt103Repository mt103Repository;
	
	@Autowired
	public Mt103ServiceImpl(Mt103Repository mt103Repository) {
		this.mt103Repository = mt103Repository;
	}
	
	@Override
	public Mt103Request save(Mt103Request mt103Request) {
		return mt103Repository.save(mt103Request);
	}

}

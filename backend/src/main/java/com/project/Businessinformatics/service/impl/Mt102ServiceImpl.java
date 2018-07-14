package com.project.Businessinformatics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.xml.Mt102Request;
import com.project.Businessinformatics.repository.Mt102Repository;
import com.project.Businessinformatics.service.Mt102Service;


@Service
public class Mt102ServiceImpl implements Mt102Service {

	@Autowired
	private Mt102Repository mt102Repository;
	
	@Override
	public Mt102Request save(Mt102Request mt102Request) {
		return mt102Repository.save(mt102Request);
	}



}

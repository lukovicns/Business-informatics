package com.project.Businessinformatics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.xml.RTGSResponse;
import com.project.Businessinformatics.repository.RTGSResponseRepository;
import com.project.Businessinformatics.service.RTGSResponseService;

@Service
public class RTGSResponseServiceImpl implements RTGSResponseService {
	
	private final RTGSResponseRepository rtgsResponseRepository;
	
	@Autowired
	public RTGSResponseServiceImpl(RTGSResponseRepository rtgsResponseRepository) {
		this.rtgsResponseRepository = rtgsResponseRepository;
	}
	
	@Override
	public RTGSResponse save(RTGSResponse save) {
		return rtgsResponseRepository.save(save);
	}
	
}

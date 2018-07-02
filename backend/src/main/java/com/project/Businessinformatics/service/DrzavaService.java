package com.project.Businessinformatics.service;

import java.util.List;

import com.project.Businessinformatics.model.Drzava;

public interface DrzavaService {
	
	Drzava findOne(Long id);
	List<Drzava> findAll();
	Drzava save(Drzava drzava);
	void delete(Drzava drzava);
}

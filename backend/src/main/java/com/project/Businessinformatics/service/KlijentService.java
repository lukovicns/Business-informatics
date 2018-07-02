package com.project.Businessinformatics.service;

import java.util.List;

import com.project.Businessinformatics.model.Klijent;

public interface KlijentService {

	Klijent findOne(Long id);
	List<Klijent> findAll();
	Klijent save(Klijent klijent);
	void delete(Klijent klijent);
	Klijent findByEmail(String email);
	Klijent findByIdAndEmail(Long id, String email);
}

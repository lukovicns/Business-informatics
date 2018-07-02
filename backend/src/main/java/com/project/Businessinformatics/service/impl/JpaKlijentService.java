package com.project.Businessinformatics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.Klijent;
import com.project.Businessinformatics.repository.KlijentRepository;
import com.project.Businessinformatics.service.KlijentService;

@Service
public class JpaKlijentService implements KlijentService {

	@Autowired
	private KlijentRepository repository;
	
	@Override
	public Klijent findOne(Long id) {
		for (Klijent klijent : findAll()) {
			if (klijent.getId() == id) {
				return klijent;
			}
		}
		return null;
	}
	
	@Override
	public Klijent findByEmail(String email) {
		for (Klijent klijent : findAll()) {
			if (klijent.getEmail().equals(email)) {
				return klijent;
			}
		}
		return null;
	}
	
	@Override
	public Klijent findByIdAndEmail(Long id, String email) {
		for (Klijent klijent : findAll()) {
			if (klijent.getId() == id && klijent.getEmail().equals(email)) {
				return klijent;
			}
		}
		return null;
	}

	@Override
	public List<Klijent> findAll() {
		return repository.findAll();
	}

	@Override
	public Klijent save(Klijent klijent) {
		return repository.save(klijent);
	}

	@Override
	public void delete(Klijent klijent) {
		repository.delete(klijent);
	}
}

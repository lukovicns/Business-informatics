package com.project.Businessinformatics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Businessinformatics.model.Drzava;
import com.project.Businessinformatics.repository.DrzavaRepository;
import com.project.Businessinformatics.service.DrzavaService;

@Service
public class JpaDrzavaService implements DrzavaService {

	@Autowired
	private DrzavaRepository repository;
	
	@Override
	public Drzava findOne(Long id) {
		for (Drzava drzava : findAll()) {
			if (drzava.getId() == id) {
				return drzava;
			}
		}
		return null;
	}

	@Override
	public List<Drzava> findAll() {
		return repository.findAll();
	}

	@Override
	public Drzava save(Drzava drzava) {
		return repository.save(drzava);
	}

	@Override
	public void delete(Drzava drzava) {
		repository.delete(drzava);
	}
}

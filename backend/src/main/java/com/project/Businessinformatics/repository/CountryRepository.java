package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Businessinformatics.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findOne(Long countryId);
	
}

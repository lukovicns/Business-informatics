package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.Klijent;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Long> {

}

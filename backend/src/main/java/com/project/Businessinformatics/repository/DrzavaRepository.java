package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.Drzava;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, Long> {

}

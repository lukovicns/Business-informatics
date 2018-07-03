package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Businessinformatics.model.user.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}

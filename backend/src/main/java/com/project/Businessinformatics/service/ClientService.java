package com.project.Businessinformatics.service;

import java.util.List;

import com.project.Businessinformatics.model.user.Client;

public interface ClientService {
	List<Client> getAllClients();
	Client getClient(Long id);
	Client save(Client client);
	Client getClientByEmail(String email);
	Client getClientByEmailAndPassword(String email, String password);
	Client findByIdAndEmail(Long id, String email);
}

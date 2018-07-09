package com.project.Businessinformatics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.user.Client;
import com.project.Businessinformatics.repository.ClientRepository;
import com.project.Businessinformatics.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClient(Long id) {
		return clientRepository.getOne(id);
	}
	
	@Override
	public Client getClientByEmail(String email) {
		for (Client client : getAllClients()) {
			if (client.getEmail().equals(email)) {
				return client;
			}
		}
		return null;
	}

	@Override
	public Client save(Client client) {
		Client c = clientRepository.save(client);
		return c;
	}

	@Override
	public Client getClientByEmailAndPassword(String email, String password) {
		Client client = getClientByEmail(email);
		return client.getPassword().equals(password) ? client : null;
	}

	@Override
	public Client findByIdAndEmail(Long id, String email) {
		Client client = getClientByEmail(email);
		return client.getId() == id ? client : null;
	}
}

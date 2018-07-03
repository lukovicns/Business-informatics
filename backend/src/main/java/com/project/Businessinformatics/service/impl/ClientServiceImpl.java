package com.project.Businessinformatics.service.impl;

import java.util.Collection;

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
	public Collection<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClient(Long id) {
		return clientRepository.getOne(id);
	}

	@Override
	public Client save(Client client) {
		Client c = clientRepository.save(client);
		return c;
	}

}

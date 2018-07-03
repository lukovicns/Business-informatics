package com.project.Businessinformatics.service;

import java.util.Collection;

import com.project.Businessinformatics.model.user.Client;

public interface ClientService {
	Collection<Client> getAllClients();
	Client getClient(Long id);
	Client save(Client client);
}

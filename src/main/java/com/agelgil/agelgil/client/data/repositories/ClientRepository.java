package com.agelgil.agelgil.client.data.repositories;

import com.agelgil.agelgil.client.data.models.Client;
import com.agelgil.agelgil.lib.data.models.auth.User;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String>{
	
	public Client findByUser(User user);

}

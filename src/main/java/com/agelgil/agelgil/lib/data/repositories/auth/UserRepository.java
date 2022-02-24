package com.agelgil.agelgil.lib.data.repositories.auth;

import com.agelgil.agelgil.lib.data.models.auth.User;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String>{


	public User findByUsernameAndVerified(String username, Boolean verified);
	
}

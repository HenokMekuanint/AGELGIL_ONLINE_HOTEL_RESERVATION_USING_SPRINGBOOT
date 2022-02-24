package com.agelgil.agelgil.lib.data.repositories.auth;

import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.VerificationToken;

import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long>{

	public VerificationToken findByUser(User user);

	public VerificationToken findByToken(String token);

}

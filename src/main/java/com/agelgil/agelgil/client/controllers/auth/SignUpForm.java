package com.agelgil.agelgil.client.controllers.auth;

import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.agelgil.agelgil.client.data.models.Client;
import com.agelgil.agelgil.client.data.repositories.ClientRepository;
import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.VerificationToken;
import com.agelgil.agelgil.lib.data.models.auth.User.Role;
import com.agelgil.agelgil.lib.extra.auth.UserManager;
import com.agelgil.agelgil.lib.services.EmailService;

import lombok.Data;


@Data
public class SignUpForm {

	@Transient
	private UserManager userManager;

	@Transient
	private EmailService emailService;

	public SignUpForm(){

	}

	public SignUpForm(UserManager userManager){
		this.userManager = userManager;
	}

	//@Pattern(regexp = "/^[a-z ,.'-]+$/i /^[a-z ,.'-]+$/i", message = "Please enter your full name.")
	private String fullName;

	@Email
	private String email;

	@Size(min = 8, message = "Password should be atleast 8 characters long.")
	private String password;

	
	private String confirmPassword;


	@AssertTrue(message = "Passwords don't match.")
	public boolean isPasswordMatching(){
		if(password == null || confirmPassword == null)
			return true;
		return password.equals(confirmPassword);
	}

	@AssertFalse(message = "User with this e-mail already exists")
	public boolean isUserExists(){
		if(email != null)
			return userManager.userExists(email);
		return false;
	}


	public Client createClient(UserManager manager, ClientRepository clientRepository, EmailService emailService){

		User user = manager.createUser(email, password, Role.CLIENT);
		VerificationToken token = manager.generateVerificationToken(user);

		emailService.sendVerificationEmail(user.getUsername(), fullName, token.getToken());
		
		Client client = new Client(user, fullName);

		clientRepository.save(client);

		return client;
	}
	
}

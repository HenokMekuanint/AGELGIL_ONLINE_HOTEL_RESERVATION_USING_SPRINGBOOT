package com.agelgil.agelgil.lib.extra.auth;

import java.security.SecureRandom;
import java.util.Base64;

import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.VerificationToken;
import com.agelgil.agelgil.lib.data.models.auth.User.Role;
import com.agelgil.agelgil.lib.data.repositories.auth.UserRepository;
import com.agelgil.agelgil.lib.data.repositories.auth.VerificationTokenRepository;
import com.agelgil.agelgil.lib.exceptions.InvalidTokenException;

import org.springframework.security.crypto.password.PasswordEncoder;


public class UserManager {
	
	private UserRepository userRepository;
	private VerificationTokenRepository tokenRepository;
	private PasswordEncoder encoder;

	private SecureRandom secureRandom = new SecureRandom();
	private Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public UserManager(UserRepository userRepository, PasswordEncoder encoder, VerificationTokenRepository verificationTokenRepository){
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.tokenRepository = verificationTokenRepository;
	}

	public User createUser(String username, String password, Role role, Boolean verified){
		
		User user = new User(
			username, 
			encoder.encode(password),
			verified,
			role
		);
		
		this.userRepository.save(user);

		return user;

	}

	public User createUser(String username, String password, Role role){
		return createUser(username, password, role, false);
	}

	public void changePassword(User user, String password){
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
	}

	public boolean userExists(String username){
		return userRepository.existsById(username);
	}

	public VerificationToken generateVerificationToken(User user){
		
		VerificationToken verificationToken = tokenRepository.findByUser(user);
		
		if(verificationToken != null)
			tokenRepository.delete(verificationToken);

		VerificationToken newToken = new VerificationToken(user, generateVerificationTokenString());
		tokenRepository.save(newToken);
		return newToken;
	}

	public void verify(String givenToken, boolean initialVerification){

		VerificationToken validToken = tokenRepository.findByToken(givenToken);
		
		if(validToken == null)
			throw new InvalidTokenException();
		
		User user = validToken.getUser();


		tokenRepository.delete(validToken);

		if(initialVerification){
			user.setVerified(true);
			userRepository.save(user);
		}

	}

	public void verify(String givenToken){
		verify(givenToken, true);
	}

	private String generateVerificationTokenString(){
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

}

package com.agelgil.agelgil.lib.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class EmailService{

	@Autowired
	private JavaMailSender emailSender;

	@Value("${com.agelgil.agelgil.host}")
	private String host;

	public void sendEmail(String to, String subject, String text){

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@agelgil.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	
	}

	public void sendVerificationEmail(String to, String name, String token){


		sendEmail(
			to, 
			"Verification", 
			String.format(
				"Hello %s, Welcome to Agelgil. Use the link below to verify your email.\n%s",
				name,
				String.format("%s/auth/verify?token=%s", host, token)
			)
		);

	}


	public void sendResetPasswordEmail(String to, String token){

		sendEmail(
			to, 
			"Reset Password", 
			String.format(
				"Use the link below to reset your password.\n%s",
				String.format("%s/auth/reset?token=%s", host, token)
			)
		);

	}
	
}

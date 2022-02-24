package com.agelgil.agelgil.lib.controllers.auth;

import javax.validation.Valid;

import com.agelgil.agelgil.client.controllers.ClientController;
import com.agelgil.agelgil.lib.controllers.auth.forms.ResetPasswordForm;
import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.VerificationToken;
import com.agelgil.agelgil.lib.data.repositories.auth.UserRepository;
import com.agelgil.agelgil.lib.data.repositories.auth.VerificationTokenRepository;
import com.agelgil.agelgil.lib.exceptions.InvalidTokenException;
import com.agelgil.agelgil.lib.exceptions.httperror.ForbiddenException;
import com.agelgil.agelgil.lib.exceptions.httperror.NotFoundException;
import com.agelgil.agelgil.lib.extra.auth.UserManager;
import com.agelgil.agelgil.lib.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController extends ClientController { //TODO: FIND A WAY TO MAKE IT WORK BY JUST EXTENDING AgelgilContoller

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;

	@GetMapping("/login")
	public String login(@RequestParam(name = "error", required = false) Object error, ModelMap modelMap){
		
		if(error != null)
			modelMap.addAttribute("loginError", true);


		return "lib/auth/login.html";
	}

	@PostMapping("/logout")
	public String logout(String redirectUrl){
		return "redirect";
	}

	@GetMapping("/auth/verify")
	public String verify(
		@RequestParam(name="token") String token
	){

		try{
			userManager.verify(token);
		}
		catch(InvalidTokenException ex){
			throw new ForbiddenException();
		}
		
		return "redirect:/?successVerification";

	}

	@GetMapping("/auth/forgot-password")
	public String forgotPassword(){

		return "lib/auth/forgot_password.html";

	}

	@PostMapping("/auth/forgot-password")
	public String handleForgotPassword(@RequestParam String email, ModelMap modelMap){

		if(!userManager.userExists(email)){
			modelMap.addAttribute("emailError", true);
			return "lib/auth/forgot_password.html";
		}


		User user = userRepository.findByUsernameAndVerified(email, true);
		VerificationToken token = userManager.generateVerificationToken(user);

		emailService.sendResetPasswordEmail(email, token.getToken());

		return "redirect:/?forgotSuccess";

	}

	@GetMapping("/auth/reset")
	public String displayResetForm(@RequestParam(name="token") String token, ModelMap modelMap){

		if(tokenRepository.findByToken(token) == null)
			throw new NotFoundException();

		return "lib/auth/reset.html";

	}

	@PostMapping("/auth/reset")
	public String handleResetForm(@Valid ResetPasswordForm form){

		VerificationToken token = tokenRepository.findByToken(form.getToken());
		if(token == null)
			throw new NotFoundException();

		String username = token.getUser().getUsername();

		try{
			userManager.verify(form.getToken(), false);
		}
		catch(InvalidTokenException ex){
			throw new ForbiddenException();
		}

		User user = userRepository.findByUsernameAndVerified(username, true);
		userManager.changePassword(user, form.getPassword());

		return "redirect:/?successResetPassword";

	}

	@ModelAttribute("resetForm")
	public ResetPasswordForm resetPasswordForm(){
		return new ResetPasswordForm();
	}



}

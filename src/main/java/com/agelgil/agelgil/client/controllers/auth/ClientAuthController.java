package com.agelgil.agelgil.client.controllers.auth;

import javax.validation.Valid;

import com.agelgil.agelgil.client.controllers.ClientController;
import com.agelgil.agelgil.client.data.repositories.ClientRepository;
import com.agelgil.agelgil.lib.extra.auth.UserManager;
import com.agelgil.agelgil.lib.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ClientAuthController extends ClientController{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private UserManager userManager;

	@Autowired
	private EmailService emailService;


	@GetMapping("/client/sign-up")
	public String displaySignUpForm(){
		return "client/auth/signup.html";
	}

	@PostMapping("/client/sign-up")
	public String handleSignUpForm(@Valid SignUpForm form, BindingResult bindingResult, @RequestParam(name = "redirect_url", required = false) String redirectUrl, ModelMap modelMap) {

		modelMap.addAttribute("signUpForm", form);

		if(bindingResult.hasErrors()){
			return "client/auth/signup.html";
		}

		form.createClient(userManager, clientRepository, emailService);

		if(redirectUrl == null)
			return "redirect:/?signUpSuccess";
		return String.format("redirect:%s", redirectUrl);
	}

	
}

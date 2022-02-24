package com.agelgil.agelgil.client.controllers;

import java.security.Principal;
import java.util.Arrays;

import javax.print.attribute.standard.PrinterInfo;

import com.agelgil.agelgil.client.controllers.auth.SignUpForm;
import com.agelgil.agelgil.client.data.models.Client;
import com.agelgil.agelgil.client.data.repositories.ClientRepository;
import com.agelgil.agelgil.lib.controllers.AgelgilController;
import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.webcontent.Tab;
import com.agelgil.agelgil.lib.data.repositories.auth.UserRepository;
import com.agelgil.agelgil.lib.extra.auth.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class ClientController extends AgelgilController {
	
	@Autowired
	private UserManager userManager;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute
	@Override
	protected void setupModelMap(ModelMap modelMap){
		super.setupModelMap(modelMap);
		modelMap.addAttribute(
			"headerTabs", Arrays.asList(
				new Tab(0, "Home", "/", 0, "header"),
				new Tab(1, "Top Rated", "/client/hotel/filter/?minStandard=5", 1, "header"),
				new Tab(2,"About Us", "/client/about-us", 2, "header")
//				new Tab(3, "Contact Us", "/client/contact-us", 3, "header")
			)
		);

		modelMap.addAttribute(
			"footerTabs", Arrays.asList(
				new Tab(0, "Home", "/", 0, "footer"),
				new Tab(1, "Top Rated", "/client/hotel/filter/?minStandard=5", 1, "footer"),
//				new Tab(2, "FAQs", "/about-us/faqs", 2, "footer"),
				new Tab(3, "About Us", "/client/about-us", 3, "footer")
//				new Tab(4, "Contact Us", "/contact-us", 4, "footer")
			)
		);

	}

	@ModelAttribute(value = "signUpForm")
	public SignUpForm signUpForm(){
		return new SignUpForm(userManager);
	}

	@ModelAttribute(value = "client")
	public Client client(Principal principal){
		if(principal == null)
			return null;
		
		return clientRepository.findByUser(
			userRepository.findByUsernameAndVerified(principal.getName(), true)
		);
	}

}

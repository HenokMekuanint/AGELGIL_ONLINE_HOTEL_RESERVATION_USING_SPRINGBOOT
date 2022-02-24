package com.agelgil.agelgil.lib.controllers;


import java.util.Arrays;

import com.agelgil.agelgil.lib.data.models.webcontent.ContactInformation;
import com.agelgil.agelgil.lib.data.repositories.webcontent.ContactInformationRespository;
import com.agelgil.agelgil.lib.services.DropBoxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;


public class AgelgilController {
	
	@Autowired
	private ContactInformationRespository contactInformationRespository;

	@Autowired
	private DropBoxService storageService;

	@ModelAttribute
	protected void setupModelMap(ModelMap modelMap){
//		modelMap.addAttribute(
//			"contactInformations", contactInformationRespository.findAll()
//		);
		modelMap.addAttribute(
			"contactInformations", Arrays.asList(
				new ContactInformation(null, "Tel", "tel: +251911223344", "+251911223344"),
				new ContactInformation(null, "E-Mail", "mailto: contact@agelgil.com", "contact@agelgil.com"),
				new ContactInformation(null, "Address", "https://map.google.com", "5-Kio, Addis Ababa, Ethiopia")
			)
		);
	}

	@ModelAttribute("media")
	protected DropBoxService fetchStorageService(){
		return storageService;
	}


}

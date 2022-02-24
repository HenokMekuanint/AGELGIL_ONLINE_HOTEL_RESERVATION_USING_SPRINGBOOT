package com.agelgil.agelgil.admin.controllers.dashboard;

import com.agelgil.agelgil.client.data.models.Client;
import com.agelgil.agelgil.client.data.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageClientsController extends AdminController{

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/admin/dashboard/clients")
	public String displayHotels(ModelMap modelMap){

		modelMap.addAttribute(
			"clients", clientRepository.findAll()
		);

		return "admin/dashboard/manage_clients.html";

	}

	@PostMapping("/admin/dashbaord/clients/delete")
	public String deleteHotel(
		@RequestParam(name = "client") Client client
	){
		clientRepository.delete(client);
		return "redirect:/admin/dashboard/clients";
	}

	
	
}

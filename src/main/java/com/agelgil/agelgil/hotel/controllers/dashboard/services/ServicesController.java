package com.agelgil.agelgil.hotel.controllers.dashboard.services;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.agelgil.agelgil.hotel.controllers.dashboard.DashboardController;
import com.agelgil.agelgil.hotel.data.models.Service;
import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.hotel.data.repositories.ServiceRepository;
import com.agelgil.agelgil.hotel.data.repositories.ServiceTypeRepository;
import com.agelgil.agelgil.lib.services.DropBoxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ServicesController extends DashboardController{
	
	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ServiceTypeRepository serviceTypeRepository;
	
	@Autowired
	private DropBoxService storageService;


	@GetMapping("/hotel/dashboard/services")
	public String displayServices(){

		return "hotel/dashboard/services.html";
	}

	@PostMapping("/hotel/dashboard/services/add")
	public String addService(
		@Valid AddServiceForm addServiceForm,
		BindingResult bindingResult,
		Principal principal
	){

		if(!bindingResult.hasErrors())
			addServiceForm.createService(storageService, serviceRepository, getHotel(principal));

		return "redirect:/hotel/dashboard/services";
	}

	@PostMapping("/hotel/dashboard/services/edit")
	public String editService(
		@Valid EditServiceForm editServiceForm,
		BindingResult bindingResult,
		Principal principal
	){

		if(!bindingResult.hasErrors())
			editServiceForm.editService(storageService, serviceRepository);

		return "redirect:/hotel/dashboard/services";
	}

	@ModelAttribute("services")
	public List<Service> fetchServices(Principal principal){
		return serviceRepository.findByHotel(getHotel(principal));
	}

	@ModelAttribute("serviceTypes")
	public Iterable<ServiceType> fetchServiceTypes(){
		return serviceTypeRepository.findAll();
	}

	@ModelAttribute("addServiceForm")
	public AddServiceForm addServiceForm(){
		return new AddServiceForm();
	}

	@ModelAttribute("editServiceForm")
	public EditServiceForm editServiceForm(){
		return new EditServiceForm();
	}

}

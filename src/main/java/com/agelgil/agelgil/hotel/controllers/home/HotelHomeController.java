package com.agelgil.agelgil.hotel.controllers.home;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.agelgil.agelgil.hotel.controllers.HotelController;
import com.agelgil.agelgil.hotel.controllers.home.forms.SignUpForm;
import com.agelgil.agelgil.hotel.data.models.City;
import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.CityRepository;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.lib.data.models.webcontent.Tab;
import com.agelgil.agelgil.lib.extra.auth.UserManager;
import com.agelgil.agelgil.lib.services.DropBoxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HotelHomeController extends HotelController{

	@Autowired
	private UserManager userManager;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private DropBoxService storageService;

	@GetMapping("/hotel")
	public String displayHomePage(
		Principal principal, 
		@RequestParam(required = false, name = "signUpSuccess") Object signUpSuccess,
		ModelMap modelMap
		){
		
		Hotel hotel = getHotel(principal);
		if(hotel == null){
			modelMap.addAttribute("signUpSuccess", signUpSuccess != null);
			return "hotel/home";
		}

		return "redirect:/hotel/dashboard";
	}

	@PostMapping("/hotel")
	public String handleSignUpForm(@Valid SignUpForm signUpForm, BindingResult bindingResult, ModelMap modelMap){
		
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("signUpError", true);
		}
		else{
			signUpForm.createHotel();
			modelMap.addAttribute("signUpSuccess", true);
		}
		return "hotel/home";
	}

	@ModelAttribute
	@Override
	protected void setupModelMap(ModelMap modelMap){
		super.setupModelMap(modelMap);
		modelMap.addAttribute(
			"footerTabs", Arrays.asList(
				new Tab(0, "Home", "/hotel", 0, "footer"),
				new Tab(2, "About Us", "/client/about-us", 3, "footer")
	//			new Tab(3, "Contact Us", "/contact-us", 4, "footer")
			)
		);
	}

	@ModelAttribute
	private SignUpForm signUpForm(){
		return new SignUpForm(userManager, hotelRepository, storageService);
	}

	@ModelAttribute("cities")
	private Iterable<City> cities(){
		return cityRepository.findAll();
	}

}

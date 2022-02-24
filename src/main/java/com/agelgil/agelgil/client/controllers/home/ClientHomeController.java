package com.agelgil.agelgil.client.controllers.home;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.agelgil.agelgil.client.controllers.ClientController;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ClientHomeController extends ClientController{


	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping("/")
	public String home(){

		return "client/home/home";
	}
	

	@ModelAttribute("availableCities")
	private Set<String> getCities(){
		final Set<String> cities = new HashSet<String>();

		hotelRepository.findAll().forEach(
			hotel -> {
				cities.add(hotel.getLocation().getCity());
			}
		);
		return cities;
	}



}

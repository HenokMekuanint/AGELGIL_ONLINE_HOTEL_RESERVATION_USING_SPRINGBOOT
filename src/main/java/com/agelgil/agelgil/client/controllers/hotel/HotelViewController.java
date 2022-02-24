package com.agelgil.agelgil.client.controllers.hotel;

import java.util.Arrays;

import com.agelgil.agelgil.client.controllers.ClientController;
import com.agelgil.agelgil.client.controllers.cart.forms.AddToCartForm;
import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.hotel.data.repositories.ServiceTypeRepository;
import com.agelgil.agelgil.lib.exceptions.httperror.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HotelViewController extends ClientController{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ServiceTypeRepository serviceTypeRepository;

	@GetMapping("/client/hotel/view/{hotel_id}")
	public String displayHotel(@PathVariable(name = "hotel_id") Long hotelId, ModelMap modelMap){
		Hotel hotel = hotelRepository.findByIdAndUserVerified(hotelId, true);
		if(hotel == null)
			throw new NotFoundException();
		modelMap.addAttribute("hotel", hotel);
		modelMap.addAttribute("serviceTypes", serviceTypeRepository.findAll());
		
		return "client/hotel/hotel_view.html";

	}

	@ModelAttribute("addToCartForm")
	private AddToCartForm addToCartForm(){
		return new AddToCartForm();
	}


}

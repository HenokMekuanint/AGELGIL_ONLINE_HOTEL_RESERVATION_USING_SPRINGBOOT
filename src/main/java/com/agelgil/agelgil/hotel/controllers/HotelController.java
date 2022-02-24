package com.agelgil.agelgil.hotel.controllers;

import java.security.Principal;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.lib.controllers.AgelgilController;
import com.agelgil.agelgil.lib.data.repositories.auth.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HotelController extends AgelgilController{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelRepository hotelRepository;


	protected Hotel getHotel(Principal principal){
		if(principal == null)
			return null;
		
		return hotelRepository.findByUserAndUserVerified(
			userRepository.findByUsernameAndVerified(principal.getName(), true),
			true
		);
	}

}

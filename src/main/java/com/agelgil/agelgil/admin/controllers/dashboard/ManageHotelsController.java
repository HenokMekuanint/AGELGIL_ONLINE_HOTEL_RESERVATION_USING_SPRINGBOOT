package com.agelgil.agelgil.admin.controllers.dashboard;

import java.util.Arrays;
import java.util.List;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.lib.data.models.webcontent.Tab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ManageHotelsController extends AdminController{
	
	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping("/admin/dashboard/hotels")
	public String displayHotels(ModelMap modelMap){

		modelMap.addAttribute(
			"hotels", hotelRepository.findAll()
		);

		return "/admin/dashboard/manage_hotels.html";

	}

	@PostMapping("/admin/dashboard/hotels/verify")
	public String verifyHotel(
		@RequestParam Hotel hotel
	){

		hotel.setVerified(true);
		hotelRepository.save(hotel);

		return "redirect:/admin/dashboard/hotels";
	}

	@PostMapping("/admin/dashbaord/hotels/delete")
	public String deleteHotel(
		@RequestParam(name = "hotel") Hotel hotel
	){
		hotelRepository.delete(hotel);
		return "redirect:/admin/dashboard/hotels";
	}

	

}

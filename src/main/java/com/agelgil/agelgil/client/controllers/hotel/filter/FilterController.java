package com.agelgil.agelgil.client.controllers.hotel.filter;

import java.util.List;

import com.agelgil.agelgil.client.controllers.ClientController;
import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FilterController extends ClientController{

	@Autowired
	protected HotelRepository hotelRepository;

	@GetMapping("/client/hotel/filter")
	public String filterByLocation(
	){
		return "client/hotel/filter/filter_view.html";
	}

}

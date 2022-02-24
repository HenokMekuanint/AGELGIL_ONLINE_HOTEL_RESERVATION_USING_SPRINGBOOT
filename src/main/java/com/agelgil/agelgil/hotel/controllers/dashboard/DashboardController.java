package com.agelgil.agelgil.hotel.controllers.dashboard;

import java.security.Principal;
import java.util.Arrays;

import com.agelgil.agelgil.hotel.controllers.HotelController;
import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.lib.data.models.webcontent.Tab;
import com.agelgil.agelgil.lib.exceptions.httperror.InternalServerException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class DashboardController  extends HotelController{
	


	@ModelAttribute
	protected void setupModelMap(ModelMap modelMap){
		super.setupModelMap(modelMap);
		modelMap.addAttribute(
			"navTabs", Arrays.asList(
				new Tab(0, "SERVICES", "/hotel/dashboard/services/", 1, "header", "fas fa-bed"),
				new Tab(1,"ORDERS", "/hotel/dashboard/orders", 2, "header", "fas fa-envelope")
			)
		);

	}

	@ModelAttribute
	protected Hotel hotel(Principal principal){
		Hotel hotel = getHotel(principal);

		if(hotel == null)
			throw new InternalServerException();

		return hotel;

	}

}

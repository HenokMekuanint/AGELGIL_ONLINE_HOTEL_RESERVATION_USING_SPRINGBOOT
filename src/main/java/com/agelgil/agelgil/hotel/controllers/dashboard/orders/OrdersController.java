package com.agelgil.agelgil.hotel.controllers.dashboard.orders;

import java.security.Principal;

import com.agelgil.agelgil.hotel.controllers.dashboard.DashboardController;
import com.agelgil.agelgil.hotel.data.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OrdersController extends DashboardController{

	@Autowired
	private OrderRepository orderRepository;


	@GetMapping("/hotel/dashboard/orders")
	public String displayOrders(
		Principal principal,
		ModelMap modelMap
	){

		modelMap.addAttribute(
			"orders", orderRepository.findByHotel(getHotel(principal))
		);

		return "hotel/dashboard/orders.html";

	}

	
}

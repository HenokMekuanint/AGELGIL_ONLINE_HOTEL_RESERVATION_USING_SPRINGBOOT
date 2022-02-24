package com.agelgil.agelgil.admin.controllers.dashboard.forms;

import javax.persistence.Transient;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.lib.data.models.auth.VerificationToken;
import com.agelgil.agelgil.lib.extra.auth.UserManager;
import com.agelgil.agelgil.lib.services.EmailService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class VertifyHotelForm {
	

	private Hotel hotel;

	public void verifyHotel(HotelRepository hotelRepository, EmailService emailService, UserManager userManager){
	 	hotel.setVerified(true);
	 	hotelRepository.save(hotel);

		VerificationToken token = userManager.generateVerificationToken(hotel.getUser());

		emailService.sendVerificationEmail(hotel.getUser().getUsername(), hotel.getName(), token.getToken());

	}

}

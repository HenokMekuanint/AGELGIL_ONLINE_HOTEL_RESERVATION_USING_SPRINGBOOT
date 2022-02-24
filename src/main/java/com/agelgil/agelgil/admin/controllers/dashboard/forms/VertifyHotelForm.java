package com.agelgil.agelgil.admin.controllers.dashboard.forms;

import javax.persistence.Transient;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class VertifyHotelForm {
	

	private Hotel hotel;

<<<<<<< HEAD
	private Boolean verified;


	private void verifyHotel(HotelRepository hotelRepository){
=======
	public void verifyHotel(HotelRepository hotelRepository){
>>>>>>> 8953eb0f8c1aeabb43e045f293faf7b38a4da5a5
		hotel.setVerified(true);
		hotelRepository.save(hotel);
	}

<<<<<<< HEAD
	public void handle(HotelRepository hotelRepository){
		if(verified)
			verifyHotel(hotelRepository);
	}


=======
>>>>>>> 8953eb0f8c1aeabb43e045f293faf7b38a4da5a5
}

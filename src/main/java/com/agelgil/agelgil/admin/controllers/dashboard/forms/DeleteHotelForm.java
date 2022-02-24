package com.agelgil.agelgil.admin.controllers.dashboard.forms;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteHotelForm {
	
	private Hotel hotel;

	public void deleteHotel(HotelRepository hotelRepository){
		hotelRepository.delete(hotel);
	}

}

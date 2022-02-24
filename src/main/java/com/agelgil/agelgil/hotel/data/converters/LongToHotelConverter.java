package com.agelgil.agelgil.hotel.data.converters;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class LongToHotelConverter implements Converter<Long, Hotel>{


	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel convert(Long source) {
		return hotelRepository.findById(source).get();
	}
	
}

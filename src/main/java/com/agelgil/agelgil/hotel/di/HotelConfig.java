package com.agelgil.agelgil.hotel.di;

import com.agelgil.agelgil.hotel.data.converters.LongToServiceConverter;
import com.agelgil.agelgil.hotel.data.repositories.ServiceRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HotelConfig {

	@Bean
	public LongToServiceConverter longToServiceConverter(ServiceRepository serviceRepository){
		return new LongToServiceConverter(serviceRepository);
	}
	
}

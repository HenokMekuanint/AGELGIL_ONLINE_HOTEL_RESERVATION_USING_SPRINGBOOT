package com.agelgil.agelgil.hotel.data.converters;


import com.agelgil.agelgil.hotel.data.models.Service;
import com.agelgil.agelgil.hotel.data.repositories.ServiceRepository;

import org.springframework.core.convert.converter.Converter;


public class LongToServiceConverter implements Converter<Long, Service>{

	ServiceRepository repository;

	public LongToServiceConverter(ServiceRepository repository){
		this.repository = repository;
	}

	@Override
	public Service convert(Long source) {
		return  repository.findById(source).get();
	}
	
}

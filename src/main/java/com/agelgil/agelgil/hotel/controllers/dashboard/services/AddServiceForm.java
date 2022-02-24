package com.agelgil.agelgil.hotel.controllers.dashboard.services;


import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Service;
import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.hotel.data.repositories.ServiceRepository;
import com.agelgil.agelgil.lib.services.StorageService;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddServiceForm {
	
	private String name;

	private Float unitPrice;

	private ServiceType serviceType;

	private MultipartFile coverImage;

	private Integer availableUnits;

	
	public Service createService(StorageService storageService, ServiceRepository serviceRepository, Hotel hotel){
		
		Service service = new Service(
			name,
			storageService.store(coverImage),
			unitPrice,
			availableUnits,
			serviceType,
			hotel
		);

		serviceRepository.save(service);
		return service;
	}
	
}

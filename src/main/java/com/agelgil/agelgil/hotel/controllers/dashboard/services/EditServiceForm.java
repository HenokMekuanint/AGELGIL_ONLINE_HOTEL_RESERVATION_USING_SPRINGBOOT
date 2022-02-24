package com.agelgil.agelgil.hotel.controllers.dashboard.services;

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
public class EditServiceForm {
	
	private Service service;

	private String name;

	private Float unitPrice;

	private MultipartFile coverImage;

	private Integer availableUnits;

	
	public Service editService(StorageService storageService, ServiceRepository serviceRepository){
		
		if(name != null)
			service.setName(name);
		
		if(unitPrice != null)
			service.setUnitPrice(unitPrice);
		
		if(coverImage != null)
			service.setCoverImage(storageService.store(coverImage));
		
		if(availableUnits != null)
			service.setAvailableUnits(availableUnits);

		serviceRepository.save(service);
		return service;
	}
	
}

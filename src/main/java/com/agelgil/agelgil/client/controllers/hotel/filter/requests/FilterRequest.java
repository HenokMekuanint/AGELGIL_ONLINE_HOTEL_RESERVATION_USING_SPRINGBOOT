package com.agelgil.agelgil.client.controllers.hotel.filter.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;


@NoArgsConstructor
@Data
public class FilterRequest {

	private String name;

	private String city;

	private Integer minStandard;

	private Integer maxStandard;

	private Float minUserRatings;

	private Float maxUserRatings;

	private Float minRoomPrice;
	
	public Float maxRoomPrice;

	public List<String> services; 


	public void setCity(String city){
		if(city.isBlank())
			this.city = null;
		else
			this.city = city; 
	}


}

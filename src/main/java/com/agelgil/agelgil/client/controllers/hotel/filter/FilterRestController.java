package com.agelgil.agelgil.client.controllers.hotel.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.agelgil.agelgil.client.controllers.hotel.filter.requests.FilterRequest;
import com.agelgil.agelgil.hotel.data.models.City;
import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.hotel.data.repositories.CityRepository;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.hotel.data.repositories.ServiceRepository;
import com.agelgil.agelgil.hotel.data.repositories.ServiceTypeRepository;
import com.agelgil.agelgil.hotel.data.specifications.HotelSpecifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FilterRestController {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ServiceTypeRepository serviceTypeRepository;


	private Specification<Hotel> generateSpecification(FilterRequest request){

		Specification<Hotel> specification = Specification
												.where(HotelSpecifications.isUserVerified(true))
												.and(request.getName() == null? null : HotelSpecifications.nameContains(request.getName()))
												.and(request.getCity() == null? null : HotelSpecifications.cityEquals(request.getCity()))
												.and(request.getMinStandard() == null? null : HotelSpecifications.standardGreaterThan(request.getMinStandard()))
												.and(request.getMaxStandard() == null? null : HotelSpecifications.standardLessThan(request.getMaxStandard()))
												.and(request.getMinUserRatings() == null? null : HotelSpecifications.userRatingGreaterThan(request.getMinUserRatings()))
												.and(request.getMaxUserRatings() == null? null : HotelSpecifications.userRatingLessThan(request.getMaxUserRatings()))
												.and(request.getMinRoomPrice() == null? null : HotelSpecifications.roomPriceGreaterThan(request.getMinRoomPrice()))
												.and(request.getMaxRoomPrice() == null? null : HotelSpecifications.roomPriceLessThan(request.getMaxRoomPrice()));
		

		if(request.getServices() != null){
			for(String serviceType : request.getServices()){
				specification = specification.and(HotelSpecifications.hasServiceType(serviceType));
			}
		}

		return specification;

	}

	@GetMapping("/api/client/hotel/filter")
	public ResponseEntity<List<Hotel>> filterHotels(FilterRequest request){
		
		List<Hotel> result = hotelRepository.findAll(
			generateSpecification(request)
		);

		return ResponseEntity.ok(result);

	}

	@GetMapping("/api/client/hotel/service/room/price_range")
	public ResponseEntity<Map<String, Float>> roomPriceRange(){
		
		return ResponseEntity.ok(
			serviceRepository.findRoomPriceRange()
		);
		
	}

	@GetMapping("/api/client/hotel/cities")
	public ResponseEntity<Iterable<City>> cities(){

		return ResponseEntity.ok(
			cityRepository.findAll()
		);

	}

	@GetMapping("/api/client/hotel/servicetypes")
	public ResponseEntity<Iterable<ServiceType>> serviceTypes(){

		return ResponseEntity.ok(
			serviceTypeRepository.findAll()
		);

	}

}

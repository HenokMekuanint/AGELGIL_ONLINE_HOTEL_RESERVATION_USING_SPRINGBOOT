package com.agelgil.agelgil.hotel.data.repositories;

import java.util.List;
import java.util.Map;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ServiceRepository extends CrudRepository<Service, Long>{

	public List<Service> findByHotel(Hotel hotel);

	@Query(value = "SELECT MIN(unit_price) as min, MAX(unit_price) as max FROM hotel_service WHERE service_type_id=2", nativeQuery = true)
	public Map<String, Float> findRoomPriceRange();

}

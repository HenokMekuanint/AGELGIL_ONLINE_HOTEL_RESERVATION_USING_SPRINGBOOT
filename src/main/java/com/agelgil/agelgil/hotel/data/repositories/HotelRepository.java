package com.agelgil.agelgil.hotel.data.repositories;

import java.util.List;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.lib.data.models.auth.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel>{
	
	public Hotel findByIdAndUserVerified(Long id, Boolean verified);

	public Hotel findByUserAndUserVerified(User user, Boolean verified);

	public List<Hotel> findByUserVerified(Boolean verified);

}

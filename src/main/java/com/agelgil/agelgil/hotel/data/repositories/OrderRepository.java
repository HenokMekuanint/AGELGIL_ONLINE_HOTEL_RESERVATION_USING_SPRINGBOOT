package com.agelgil.agelgil.hotel.data.repositories;

import java.util.List;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Order;

import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Long>{

	public List<Order> findByHotel(Hotel hotel);

}

package com.agelgil.agelgil.client.data.repositories;

import com.agelgil.agelgil.client.data.models.Cart.CartItem;

import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	
}

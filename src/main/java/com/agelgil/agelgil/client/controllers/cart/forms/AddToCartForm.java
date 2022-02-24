package com.agelgil.agelgil.client.controllers.cart.forms;


import javax.persistence.Transient;

import com.agelgil.agelgil.client.data.models.Cart;
import com.agelgil.agelgil.client.data.models.Cart.CartItem;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;
import com.agelgil.agelgil.client.data.repositories.CartRepository;
import com.agelgil.agelgil.hotel.data.models.Service;
import com.agelgil.agelgil.hotel.data.repositories.ServiceRepository;

import org.springframework.core.convert.converter.Converter;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddToCartForm {

	@Transient
	private Cart cart;

	@Transient
	private CartItemRepository cartItemRepository;

	private Service service;

	private Integer units;


	public CartItem addToCart(){
		CartItem cartItem = new CartItem(service, units, cart);
		cartItemRepository.save(cartItem);
		
		return cartItem;
	} 

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public void setCartItemRepository(CartItemRepository cartItemRepository){
		this.cartItemRepository = cartItemRepository;
	}

	
	
}


package com.agelgil.agelgil.client.data.converters;

import com.agelgil.agelgil.client.data.models.Cart.CartItem;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;

import org.springframework.core.convert.converter.Converter;


public class LongToCartItemConverter implements Converter<Long, CartItem>{

	private CartItemRepository cartItemRepository;

	public LongToCartItemConverter(CartItemRepository cartItemRepository){
		this.cartItemRepository = cartItemRepository;
	}

	@Override
	public CartItem convert(Long source) {
		return cartItemRepository.findById(source).get();

	}
	
}

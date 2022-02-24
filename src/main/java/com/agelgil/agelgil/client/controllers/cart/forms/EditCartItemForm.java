package com.agelgil.agelgil.client.controllers.cart.forms;

import javax.persistence.Transient;
import javax.validation.constraints.Min;

import com.agelgil.agelgil.client.data.models.Cart;
import com.agelgil.agelgil.client.data.models.Cart.CartItem;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EditCartItemForm {
	
	@Transient
	private CartItemRepository cartItemRepository;

	private CartItem cartItem;

	@Min(value = 1)
	private Integer units;

	public boolean doesOwnCart(Cart cart){
		return cart.getItems().contains(cartItem);
	}

	public void editCartItem(){
		cartItem.setUnits(units);
		cartItemRepository.save(cartItem);
	}

	public void setCartItemRepository(CartItemRepository cartItemRepository){
		this.cartItemRepository = cartItemRepository;
	}

	
}

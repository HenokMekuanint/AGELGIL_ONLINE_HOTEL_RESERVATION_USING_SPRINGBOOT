package com.agelgil.agelgil.client.controllers.cart.forms;

import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;

import com.agelgil.agelgil.client.data.models.Cart;
import com.agelgil.agelgil.client.data.models.Cart.CartItem;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;
import com.agelgil.agelgil.client.di.CartManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoveFromCartForm {

	@Transient
	private CartItemRepository cartItemRepository;

	private CartItem cartItem; 
	

	public boolean doesOwnCart(Cart cart){
		return cart.getItems().contains(cartItem);
	}

	public void removeCartItem(){
		cartItemRepository.delete(cartItem);
	}

	public void setCartItemRepository(CartItemRepository cartItemRepository){
		this.cartItemRepository = cartItemRepository;
	}

}

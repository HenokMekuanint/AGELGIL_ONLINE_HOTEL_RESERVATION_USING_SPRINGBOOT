package com.agelgil.agelgil.client.controllers.cart;

import java.security.Principal;

import javax.validation.Valid;

import com.agelgil.agelgil.client.controllers.ClientController;
import com.agelgil.agelgil.client.controllers.cart.forms.AddToCartForm;
import com.agelgil.agelgil.client.controllers.cart.forms.EditCartItemForm;
import com.agelgil.agelgil.client.controllers.cart.forms.RemoveFromCartForm;
import com.agelgil.agelgil.client.data.models.Cart;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;
import com.agelgil.agelgil.client.di.CartManager;
import com.agelgil.agelgil.lib.exceptions.httperror.ForbiddenException;
import com.agelgil.agelgil.lib.exceptions.httperror.InternalServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CartController extends ClientController {

	@Autowired
	private CartManager cartManager;

	@Autowired
	private CartItemRepository cartItemRepository;

	@GetMapping("/client/cart/view")
	public String displayCart(ModelMap modelMap, Principal principal){
		modelMap.addAttribute("cart", cartManager.getCart(principal));

		modelMap.addAttribute("removeFromCartForm", new RemoveFromCartForm());

		return "client/cart/cart_view.html";
		
	}

	@PostMapping("/client/cart/remove")
	public String removeFromCart(
		@Valid RemoveFromCartForm removeFromCartForm,
		BindingResult bindingResult,
		Principal principal,
		@RequestParam(name = "redirect_url") String redirectUrl
	){
		
		if(bindingResult.hasErrors())
			throw new InternalServerException();
		
		if(!removeFromCartForm.doesOwnCart(cartManager.getCart(principal)))
			throw new ForbiddenException();
		
		removeFromCartForm.setCartItemRepository(cartItemRepository);

		removeFromCartForm.removeCartItem();

		return String.format("redirect:%s", redirectUrl);

	}

	@PostMapping("/client/cart/add")
	public String addToCart(
		@Valid AddToCartForm addToCartForm,
		BindingResult bindingResult,
		Principal principal,
		@RequestParam(name = "redirect_url") String redirectUrl
		){

		if(bindingResult.hasErrors())
			throw new InternalServerException();
		
		addToCartForm.setCart(cartManager.getCart(principal));
		addToCartForm.setCartItemRepository(cartItemRepository);


		addToCartForm.addToCart();

		return String.format("redirect:%s", redirectUrl);

	}

	@PostMapping("/client/cart/edit")
	public String editCartItem(
		@Valid EditCartItemForm editCartItemForm,
		BindingResult bindingResult,
		Principal principal,
		@RequestParam(name = "redirect_url") String redirectUrl
	){

		if(bindingResult.hasErrors())
			return String.format("redirect:%s", redirectUrl);

		if(!editCartItemForm.doesOwnCart(cartManager.getCart(principal)))
			throw new ForbiddenException();

		editCartItemForm.setCartItemRepository(cartItemRepository);
		editCartItemForm.editCartItem();

		return String.format("redirect:%s", redirectUrl);
	}

	@PostMapping("/client/cart/checkout")
	public String checkOut(Principal principal){
		Cart cart = cartManager.getCart(principal);
		cartManager.createOrder(cart);
		return "redirect:/";
	}

}

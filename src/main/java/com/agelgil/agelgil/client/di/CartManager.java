package com.agelgil.agelgil.client.di;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.agelgil.agelgil.client.data.models.Cart;
import com.agelgil.agelgil.client.data.models.Client;
import com.agelgil.agelgil.client.data.models.Cart.CartItem;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;
import com.agelgil.agelgil.client.data.repositories.CartRepository;
import com.agelgil.agelgil.client.data.repositories.ClientRepository;
import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Order;
import com.agelgil.agelgil.hotel.data.repositories.OrderRepository;
import com.agelgil.agelgil.lib.data.repositories.auth.UserRepository;
import com.agelgil.agelgil.lib.extra.auth.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CartManager {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private OrderRepository orderRepository;


	public CartManager(){

	}

	public Cart getCart(String username){
		Client client = clientRepository.findByUser(
			userRepository.findByUsernameAndVerified(username, true)
			);
		if(client.getCart() == null)
			return createCart(client);
		
		return client.getCart();
		
	}

	public Cart getCart(Principal principal){
		return getCart(principal.getName());
	}

	public void clearCart(Cart cart){
		cart.getItems().forEach(item -> cartItemRepository.delete(item));
	}



	public List<Order> createOrder(Cart cart){

		List<Order> orders = new ArrayList<Order>();
		Set<Hotel> hotels = getHotels(cart);

		hotels.forEach(
			hotel -> {
				Order order = new Order(hotel, cart.getClient());
				orderRepository.save(order);
				List<CartItem> items = filterByHotel(cart, hotel);
				items.forEach(
					item -> {
						item.setCart(null);
						item.setOrder(order);
						cartItemRepository.save(item);
					}
				);
				orders.add(order);
			}
		);

		return orders;
	}

	private List<CartItem> filterByHotel(Cart cart, final Hotel hotel){
		return cart.getItems().stream().filter(
			item -> item.getService().getHotel().equals(hotel)
		).collect(Collectors.toList());
	}

	private Set<Hotel> getHotels(Cart cart){
		final Set<Hotel> hotels = new HashSet<Hotel>();

		cart.getItems().forEach(
			item -> hotels.add(item.getService().getHotel())
		);

		return hotels;

	}

	private Cart createCart(Client client){
		Cart cart = new Cart();
		cart.setClient(client);
		cartRepository.save(cart);
		return cart;
	}

}

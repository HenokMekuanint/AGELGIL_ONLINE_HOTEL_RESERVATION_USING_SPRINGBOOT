package com.agelgil.agelgil.client.data.models;

import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateMLet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agelgil.agelgil.hotel.data.models.Order;
import com.agelgil.agelgil.hotel.data.models.Service;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "client_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@JoinColumn(nullable = false, unique = true)
	@OneToOne
	private Client client;

	@OneToMany(mappedBy = "cart")
	private List<CartItem> items;

	
	public float getTotalPrice(){
		float totalPrice = 0;
		for( CartItem item : items)
			totalPrice += item.getPrice();
		return totalPrice;
	}


	
	@NoArgsConstructor
	@Entity
	@Data
	@Table(name = "client_cart_item")
	public static class CartItem{

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@JoinColumn(nullable = false)
		@ManyToOne(cascade = CascadeType.REMOVE)
		private Service service;

		@ManyToOne
		private Cart cart;

		private Integer units;

		@ManyToOne(cascade = CascadeType.REMOVE)
		private Order order;

		public CartItem(Service service, Integer quantity, Cart cart){
			this.service = service;
			this.units = quantity;
			this.cart = cart;
		}

		public float getPrice(){
			return units * service.getUnitPrice();
		}

		public String toString(){
			return this.service.getName();
		}

	}

}

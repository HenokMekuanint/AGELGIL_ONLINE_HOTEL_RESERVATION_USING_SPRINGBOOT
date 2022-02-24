package com.agelgil.agelgil.hotel.data.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.agelgil.agelgil.client.data.models.Client;
import com.agelgil.agelgil.client.data.models.Cart.CartItem;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "hotel_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Hotel hotel;

	@ManyToOne
	private Client client;

	@OneToMany(mappedBy = "order")
	private List<CartItem> items;

	public Order(Hotel hotel, Client client){
		this.hotel = hotel;
		this.client = client;
	}


}

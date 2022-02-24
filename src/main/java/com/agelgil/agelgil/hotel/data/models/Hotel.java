package com.agelgil.agelgil.hotel.data.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.agelgil.agelgil.hotel.data.models.Service.ServiceType;
import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(
	name = "hotel_hotel",
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_username"})
	)
public class Hotel extends UserType {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User user;

	private String name;

	@Embedded
	private Location location;

	private Integer standard;

	private Float rating = 8.4f;

	private String legalDocument;

	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Service> services;

	private boolean verified = false;

	private String profileImage;

	private String coverImage;

	@Column(length = 1000)
	private String description;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Image> gallery;

	public Hotel(User user, String name, Location location, int standard, String legalDocuement, boolean verified,String profileImage, String coverImage, String description){
		this.user = user;
		this.name = name;
		this.location = location;
		this.standard = standard;
		this.legalDocument = legalDocuement;
		this.verified = verified;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
		this.description = description;
	}

	public List<Service> getServiceByType(ServiceType serviceType){
		return getServices()
					.stream()
					.filter(service -> service.getServiceType() == serviceType)
					.toList();

	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Embeddable
	public static class Location{

		private String city;

		private String plusCode;

	} 

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Entity
	@Table(name = "hotel_hotel_gallery")
	public static class Image{

		@Id
		private String file;

		@ManyToOne
		private Hotel hotel;


	}
}

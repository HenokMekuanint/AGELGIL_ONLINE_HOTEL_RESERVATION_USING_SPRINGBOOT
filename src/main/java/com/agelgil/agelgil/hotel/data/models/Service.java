package com.agelgil.agelgil.hotel.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "hotel_service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String coverImage;

	private Float unitPrice;

	private Integer availableUnits;

	@ManyToOne
	private ServiceType serviceType;

	@JsonIgnore
	@ManyToOne
	private Hotel hotel;


	public Service(String name, String coverImage, Float unitPrice, Integer availableUnits, ServiceType serviceType, Hotel hotel){
		this.name = name;
		this.coverImage = coverImage;
		this.unitPrice = unitPrice;
		this.availableUnits = availableUnits;
		this.serviceType = serviceType;
		this.hotel = hotel;
	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Entity
	@Table(
		name = "hotel_service_type",
		uniqueConstraints = @UniqueConstraint(columnNames =  {"name"})
		)
	public static class ServiceType{

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Integer id;
		
		@Column(unique = true)
		private String name;

		@Enumerated(EnumType.STRING)
		private Unit unit;
	
		public ServiceType(String name, Unit unit){
			this.name = name;
			this.unit = unit;
		}

		public static enum Unit{
			NIGHT, DAY, MONTH, WEEK
		}

	}
	
}

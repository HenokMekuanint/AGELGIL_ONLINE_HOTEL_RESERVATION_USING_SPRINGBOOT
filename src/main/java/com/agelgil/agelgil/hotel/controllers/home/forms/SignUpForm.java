package com.agelgil.agelgil.hotel.controllers.home.forms;

import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.agelgil.agelgil.hotel.data.models.Hotel;
import com.agelgil.agelgil.hotel.data.models.Hotel.Location;
import com.agelgil.agelgil.hotel.data.repositories.HotelRepository;
import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.User.Role;
import com.agelgil.agelgil.lib.extra.auth.UserManager;
import com.agelgil.agelgil.lib.services.StorageService;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class SignUpForm {
	
	@Transient
	private UserManager userManager;

	@Transient
	private HotelRepository hotelRepository;

	@Transient
	private StorageService storageService;

	public SignUpForm(UserManager userManager, HotelRepository hotelRepository, StorageService storageService){
		this.userManager = userManager;
		this.hotelRepository = hotelRepository;
		this.storageService = storageService;
	}


	@NotBlank
	private String name;

	@NotBlank
	private String city;

	@NotBlank
	private String plusCode;

	@NotNull
	@Min(value = 1)
	@Max(value = 6)
	private Integer standard;

	@NotNull
	private MultipartFile legalDocument;

	@NotNull
	private MultipartFile profileImage;

	@NotNull
	private MultipartFile coverImage;

	@NotBlank
	private String description;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min=8, message = "Password should be at least 8 characters long")
	private String password;

	@NotBlank
	private String confirmPassword;


	@AssertTrue(message = "Passwords do not match.")
	public boolean isPasswordMatching(){
		if(password == null || confirmPassword == null)
			return true;
		return password.equals(confirmPassword);
	}

	@AssertFalse(message = "User with this email already exists.")
	public boolean isUserExists(){
		if(email == null)
			return false;
		return userManager.userExists(email);
	}

	public Hotel createHotel(){
		User user = userManager.createUser(email, password, Role.HOTEL);

		Hotel hotel = new Hotel(
			user,
			name,
			new Location(
				city,
				plusCode
			),
			standard,
			storageService.store(legalDocument),
			storageService.store(profileImage),
			storageService.store(coverImage),
			description,
			false
		);
		hotelRepository.save(hotel);
		return hotel;
	}



}

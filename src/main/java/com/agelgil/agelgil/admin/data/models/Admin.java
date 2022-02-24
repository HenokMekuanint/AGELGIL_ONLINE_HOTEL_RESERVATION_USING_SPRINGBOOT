package com.agelgil.agelgil.admin.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.UserType;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(
	name = "admin_admin",
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_username"})
	)
public class Admin extends UserType{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(nullable = false, unique = true)
	@ManyToOne
	private User user;
	
	private String firstName;
	private String lastName;


	public Admin(User user, String firstName, String lastName){
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
}

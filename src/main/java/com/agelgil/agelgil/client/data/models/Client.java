package com.agelgil.agelgil.client.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.agelgil.agelgil.lib.data.models.auth.User;
import com.agelgil.agelgil.lib.data.models.auth.UserType;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(
	name = "client_client",
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_username"})
	)
public class Client extends UserType{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User user;

	private String fullName;

	@OneToOne(mappedBy = "client")
	private Cart cart;


	public Client(User user, String fullName){
		this.user = user;
		this.fullName = fullName;
	}

}

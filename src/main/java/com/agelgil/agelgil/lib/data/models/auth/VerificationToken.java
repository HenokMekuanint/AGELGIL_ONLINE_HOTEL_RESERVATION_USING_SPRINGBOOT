package com.agelgil.agelgil.lib.data.models.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "lib_auth_verficationtoken")
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(unique = true)
	private User user;

	private String token;

	public VerificationToken(User user, String token){
		this.user = user;
		this.token = token;
	}
	
}

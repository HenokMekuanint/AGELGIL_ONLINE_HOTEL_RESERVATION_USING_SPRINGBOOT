package com.agelgil.agelgil.lib.data.models.webcontent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lib_webcontents_contactinformation")
public class ContactInformation {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String url;
	
	@Column(nullable = false)
	private String value;

	
}

package com.agelgil.agelgil.lib.data.models.webcontent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lib_webcontents_tab")
public class Tab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String url;
    
	@Column(nullable = false)
	private Integer relativeOrder;

	@Column(nullable = false)
	private String role;

	private String icon;


	public Tab(Integer id, String text, String url, Integer relativeOrder, String role){
		this.id = id;
		this.text = text;
		this.url = url;
		this.relativeOrder = relativeOrder;
		this.role = role;
	}

}

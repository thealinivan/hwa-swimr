package com.qa.hwa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String postcode;
	@ManyToOne
	private Club club;
	
	public Place() {};
	
	public Place(Integer id, String name, String postcode) {
		super();
		this.id = id;
		this.name = name;
		this.postcode = postcode;
	}
	
	public Place(String name, String postcode) {
		super();
		this.name = name;
		this.postcode = postcode;
	}

	public Place(Integer id, String name, String postcode, Club club) {
		super();
		this.id = id;
		this.name = name;
		this.postcode = postcode;
		this.club = club;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
}



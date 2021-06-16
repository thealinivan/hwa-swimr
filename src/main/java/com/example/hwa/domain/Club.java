package com.example.hwa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "club")
	private List<Place> places = new ArrayList<>();
	
	public Club() {};
	
	public Club(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Club(String name) {
		super();
		this.name = name;
	}
	
	public Club(String name, List<Place> places) {
		super();
		this.name = name;
		this.places = places;
	}

	public Club(Integer id, String name, List<Place> places) {
		super();
		this.id = id;
		this.name = name;
		this.places = places;
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
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	
	
	
}

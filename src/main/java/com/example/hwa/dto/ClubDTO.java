package com.example.hwa.dto;

import java.util.List;

public class ClubDTO {
	private Integer id;
	private String name;
	private List<PlaceDTO> places;
	
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
	public List<PlaceDTO> getPlaces() {
		return places;
	}
	public void setPlaces(List<PlaceDTO> places) {
		this.places = places;
	}
	
	
}

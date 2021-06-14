package com.example.hwa.dto;

public class PlaceDTO {
	private Integer id;
	private String name;
	private String postcode;
	
	public PlaceDTO() {}

	public PlaceDTO(String name, String postcode) {
		super();
		this.name = name;
		this.postcode = postcode;
	}

	public PlaceDTO(Integer id, String name, String postcode) {
		super();
		this.id = id;
		this.name = name;
		this.postcode = postcode;
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
	};
	
	
}

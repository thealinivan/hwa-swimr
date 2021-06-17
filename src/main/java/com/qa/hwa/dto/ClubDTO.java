package com.qa.hwa.dto;

import java.util.List;

public class ClubDTO {
	private Integer id;
	private String name;
	private List<PlaceDTO> places;
	
	public ClubDTO() {}

//	public ClubDTO(String name, List<PlaceDTO> places) {
//		super();
//		this.name = name;
//		this.places = places;
//	}
	
	public ClubDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ClubDTO(Integer id, String name, List<PlaceDTO> places) {
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
	public List<PlaceDTO> getPlaces() {
		return places;
	}
	public void setPlaces(List<PlaceDTO> places) {
		this.places = places;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((places == null) ? 0 : places.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubDTO other = (ClubDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (places == null) {
			if (other.places != null)
				return false;
		} else if (!places.equals(other.places))
			return false;
		return true;
	}
	
	
	
	
}

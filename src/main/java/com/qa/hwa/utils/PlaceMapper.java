package com.qa.hwa.utils;

import org.springframework.stereotype.Service;

import com.qa.hwa.domain.Place;
import com.qa.hwa.dto.PlaceDTO;

@Service
public class PlaceMapper implements Mapper<Place, PlaceDTO> {

	@Override
	public PlaceDTO mapToDTO(Place place) {
		PlaceDTO dto = new PlaceDTO();
		dto.setId(place.getId());
		dto.setName(place.getName());
		dto.setPostcode(place.getPostcode());
		return dto;
	}

	@Override
	public Place mapFromDTO(PlaceDTO dto) {
		return null;
	}

	

	
}

package com.example.hwa.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hwa.domain.Club;
import com.example.hwa.domain.Place;
import com.example.hwa.dto.ClubDTO;
import com.example.hwa.dto.PlaceDTO;

@Service
public class ClubMapper implements Mapper<Club, ClubDTO> {
	
	private PlaceMapper placeMapper;
	
	public ClubMapper(PlaceMapper placeMapper) {
		super();
		this.placeMapper = placeMapper;
	}

	@Override
	public ClubDTO mapToDTO(Club club) {
		ClubDTO dto = new ClubDTO();
		List<PlaceDTO> places = new ArrayList<>();
		dto.setId(club.getId());
		dto.setName(club.getName());
		for (Place place : club.getPlaces()) {
			places.add(this.placeMapper.mapToDTO(place));
		}
		dto.setPlaces(places);
		return dto;
	}

	@Override
	public Club mapFromDTO(ClubDTO dto) {
		Club club = new Club();
		List<Place> places = new ArrayList<>();
		club.setId(dto.getId());
		club.setName(dto.getName());
		for (PlaceDTO placeDTO : dto.getPlaces()) {
			places.add(this.placeMapper.mapFromDTO(placeDTO));
		}
		club.setPlaces(places);
		return club;
	}
	
}

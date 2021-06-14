package com.example.hwa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.hwa.domain.Place;
import com.example.hwa.dto.PlaceDTO;
import com.example.hwa.repo.PlaceRepo;
import com.example.hwa.utils.PlaceMapper;

@Service
public class PlaceService {
	private PlaceRepo repo;
	private PlaceMapper mapper;
	public PlaceService(PlaceRepo repo, PlaceMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	//create
	public PlaceDTO createPlace(Place place) {
		Place saved = this.repo.save(place);
		return this.mapper.mapToDTO(saved);
	}

	//read all
	public List<PlaceDTO> readPlaces() {
		List<Place> places = this.repo.findAll();
		List<PlaceDTO> dtos = new ArrayList<>();
		PlaceDTO dto = null;
		for (Place place : places) {
			dto = this.mapper.mapToDTO(place);
			dtos.add(dto);
		}
		return dtos;
	}

	//update
	public PlaceDTO updatePlace(Integer id, Place place) {
		Place current = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		current.setName(place.getName()); 
		current.setPostcode(place.getPostcode());
		Place updated = this.repo.save(current); 
		return this.mapper.mapToDTO(updated);
	}
	
}

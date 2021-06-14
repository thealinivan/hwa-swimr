package com.example.hwa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hwa.domain.Place;
import com.example.hwa.dto.PlaceDTO;
import com.example.hwa.service.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {
	private PlaceService service;
	
	@Autowired
	public PlaceController(PlaceService service) {
		super();
		this.service = service;
	}
	
	//create
	@RequestMapping("/create")
	public PlaceDTO createPlace(@RequestBody Place place) {
		return this.service.createPlace(place);
	}
	
}

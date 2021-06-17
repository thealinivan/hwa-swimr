package com.qa.hwa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hwa.domain.Place;
import com.qa.hwa.dto.PlaceDTO;
import com.qa.hwa.service.PlaceService;


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
	
	//read all
	@GetMapping("/readAll")
	public List<PlaceDTO> readPlaces() {
		return this.service.readPlaces();
	}
	
	//update
	@PutMapping("/update/{id}")
	public PlaceDTO updatePlace(@RequestBody Place place, @PathVariable Integer id) {
		return this.service.updatePlace(id, place);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public boolean deletePlace(@PathVariable int id) {
		return this.service.deletePlace(id);
	}
	
	
	
}

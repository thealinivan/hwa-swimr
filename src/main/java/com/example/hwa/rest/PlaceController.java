package com.example.hwa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}

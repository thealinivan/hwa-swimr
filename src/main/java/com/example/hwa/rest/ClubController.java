package com.example.hwa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hwa.service.ClubService;

@RestController
@RequestMapping("/clubs")
public class ClubController {
	private ClubService service;

	@Autowired
	public ClubController(ClubService service) {
		super();
		this.service = service;
	}
	
	
}

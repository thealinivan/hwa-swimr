package com.example.hwa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hwa.domain.Club;
import com.example.hwa.dto.ClubDTO;
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
	
	//create
	@PostMapping("/create")
	public ClubDTO createClub(@RequestBody Club club) {
		return this.service.createClub(club);
	}
	
	//readAll
	@GetMapping("/readAll")
	public List<ClubDTO> readClubs(){
		return this.service.readClubs();
	}
	
	//update
	@PutMapping("/update/{id}")
	public ClubDTO updateClub(@RequestBody Club club, @PathVariable int id) {
		return this.service.updateClub(id, club);
	}

	//delete
	@DeleteMapping("/delete/{id}")
	public boolean deleteClub(@PathVariable int id) {
		return this.service.deleteClub(id);
	}	
	
	//read by id
	@GetMapping("/read/{id}")
	public ClubDTO readById(@PathVariable int id) {
		return this.service.readById(id);
	}
	
}

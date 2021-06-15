package com.example.hwa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.hwa.domain.Club;
import com.example.hwa.dto.ClubDTO;
import com.example.hwa.repo.ClubRepo;
import com.example.hwa.utils.ClubMapper;

@Service
public class ClubService {
	private ClubRepo repo;
	private ClubMapper mapper;
	public ClubService(ClubRepo repo, ClubMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	//create
	public ClubDTO createClub(Club club) {
		Club saved = this.repo.save(club);
		return this.mapper.mapToDTO(saved);
	}

	//read
	public List<ClubDTO> readClubs() {
		List<Club> clubs = this.repo.findAll();
		List<ClubDTO> dtos = new ArrayList<>();
		for (Club club : clubs) {
			dtos.add(this.mapper.mapToDTO(club));
		}
		return dtos;
	}
	
	//update
	public ClubDTO updateClub(int id, Club club) {
		Club current = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		current.setName(club.getName());
		Club updated = this.repo.save(current);
		return this.mapper.mapToDTO(updated);
	}
	
	//delete
	public boolean deleteClub(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	//read by id
	public ClubDTO readById(int id) {
		Club club = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); 
		return this.mapper.mapToDTO(club);
	}
	

	
	
}

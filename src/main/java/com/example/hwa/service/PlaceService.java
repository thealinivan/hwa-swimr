package com.example.hwa.service;

import org.springframework.stereotype.Service;

import com.example.hwa.repo.ClubRepo;
import com.example.hwa.utils.ClubMapper;

@Service
public class PlaceService {
	private ClubRepo repo;
	private ClubMapper mapper;
	public PlaceService(ClubRepo repo, ClubMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
}

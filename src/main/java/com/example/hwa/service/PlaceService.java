package com.example.hwa.service;

import org.springframework.stereotype.Service;

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
	
}

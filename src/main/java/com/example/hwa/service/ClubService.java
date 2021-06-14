package com.example.hwa.service;

import org.springframework.stereotype.Service;
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
	
}

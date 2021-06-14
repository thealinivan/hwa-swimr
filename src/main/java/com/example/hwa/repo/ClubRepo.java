package com.example.hwa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hwa.domain.Club;
import com.example.hwa.domain.Place;

@Repository
public interface ClubRepo extends JpaRepository<Club, Integer> {

}

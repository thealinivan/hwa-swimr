package com.qa.hwa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hwa.domain.Club;
import com.qa.hwa.domain.Place;

@Repository
public interface ClubRepo extends JpaRepository<Club, Integer> {

}

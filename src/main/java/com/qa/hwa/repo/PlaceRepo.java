package com.qa.hwa.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.hwa.domain.Place;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Integer> {
	List<Place> findByName(String name);
}

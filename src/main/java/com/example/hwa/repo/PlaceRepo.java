package com.example.hwa.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hwa.domain.Place;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Integer> {
	List<Place> findByName(String name);
}

package com.example.hwa.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.hwa.domain.Club;
import com.example.hwa.domain.Place;
import com.example.hwa.repo.ClubRepo;
import com.example.hwa.repo.PlaceRepo;
import com.example.hwa.service.ClubService;
import com.example.hwa.service.PlaceService;

@SpringBootTest
@ActiveProfiles("test")

public class ClubServiceTest {
	@Autowired
	private ClubService service;
	
	@MockBean
	private ClubRepo repo;
		
	//create
	@Test
	void testCreate() {
		//GIVEN
		Club club = new Club("Team Birch");
		//WHEN
		Mockito.when(this.repo.save(club)).thenReturn(club);
		//THEN
		assertThat(this.service.createClub(club)).isEqualTo(club);
		Mockito.verify(this.repo, Mockito.times(1)).save(club);
	}
		
	//read
	@Test
	void testReadAll() {
		//GIVEN
		Club club = new Club("Team Birch");
        List<Club> clubs = List.of(club);
		//WHEN
		Mockito.when(this.repo.save(club)).thenReturn(club);
		Mockito.when(this.repo.findAll()).thenReturn(clubs);
		//THEN
		assertThat(this.service.readClubs()).isEqualTo(clubs);
		Mockito.verify(this.repo, Mockito.times(1)).save(club);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	
	}
	
	//update
	@Test
	void testUpdate() {
		//GIVEN
		Integer testId = 1;
		Club testData = new Club("Team Birch");
		Club currentClub = new Club(1, "Team Birch");
		Club updatedClub = new Club(testId, "Team Birch");
		//WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(currentClub));
		Mockito.when(this.repo.save(updatedClub)).thenReturn(updatedClub);
		//THEN
		assertThat(this.service.updateClub(testId, testData)).isEqualTo(updatedClub);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedClub);
	}
	
	//delete
	@Test
	void testDelete() {
		//GIVEN
		Integer testId = 1;
		//WHEN
		Mockito.when(this.repo.existsById(testId)).thenReturn(false);
		//THEN
		assertThat(this.service.deleteClub(testId)).isEqualTo(!false);
	
		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}
}

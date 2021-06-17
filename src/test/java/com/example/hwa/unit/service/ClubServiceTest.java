package com.example.hwa.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.hwa.domain.Club;
import com.example.hwa.dto.ClubDTO;
import com.example.hwa.repo.ClubRepo;
import com.example.hwa.service.ClubService;

@SpringBootTest
@ActiveProfiles("test")

public class ClubServiceTest {
	@Autowired
	private ClubService service;
	
	@MockBean
	private ClubRepo repo;
		
	//create
	@Test
	void testCreateClub() {
		//GIVEN
		Club saved = new Club(2, "Team Birch");
		ClubDTO savedClubDTO = new ClubDTO(2, "Team Birch", new ArrayList<>());
		//WHEN
		Mockito.when(this.repo.save(saved)).thenReturn(saved);
		//THEN
		assertThat(this.service.createClub(saved)).usingRecursiveComparison().isEqualTo(savedClubDTO);
		
	}
		
	//read
	@Test
	void testReadClubs() {
		//GIVEN
		Club actual= new Club(1, "Team Birch");
        List<Club> clubs = List.of(actual);
        ClubDTO currentClubDTO = new ClubDTO(1, "Team Birch", new ArrayList<>() );
        List<ClubDTO> clubsDTOs = List.of(currentClubDTO);
		//WHEN
		Mockito.when(this.repo.findAll()).thenReturn(clubs);
		//THEN
		assertThat(this.service.readClubs()).isEqualTo(clubsDTOs);
	
	}
	
	//update
	@Test
	void testUpdateClub() {
		//GIVEN
		Integer actualId = 1;
		Club currentClub = new Club(actualId, "Team Birch");
		Club updatedClub = new Club(actualId, "Team ELm");
		ClubDTO updatedClubDTO = new ClubDTO(actualId, "Team ELm", new ArrayList<>());
		//WHEN
		Mockito.when(this.repo.findById(actualId)).thenReturn(Optional.of(currentClub));
		Mockito.when(this.repo.save(updatedClub)).thenReturn(updatedClub);
		//THEN
		assertThat(this.service.updateClub(actualId, updatedClub)).usingRecursiveComparison().isEqualTo(updatedClubDTO);
	}
	
	//delete
	@Test
	void testDeleteClub() {
		//GIVEN
		Integer actualId = 1;
		//WHEN
		Mockito.when(this.repo.existsById(actualId)).thenReturn(false);
		//THEN
		assertThat(this.service.deleteClub(actualId)).isEqualTo(!false);
	}
	
	//read by id
	@Test
	void testReadById() {
		//GIVEN
		Integer actualId = 1;
		Club actual = new Club(actualId, "Team Birch");
		ClubDTO clubDTO = new ClubDTO(actualId, "Team Birch", new ArrayList<>());
		//WHEN
		Mockito.when(this.repo.findById(actualId)).thenReturn(Optional.of(actual));
		//THEN
		assertThat(this.service.readById(actualId)).isEqualTo(clubDTO);
	}
}

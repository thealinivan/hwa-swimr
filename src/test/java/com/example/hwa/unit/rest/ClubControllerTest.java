package com.example.hwa.unit.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.hwa.domain.Club;
import com.example.hwa.dto.ClubDTO;
import com.example.hwa.rest.ClubController;
import com.example.hwa.service.ClubService;


@SpringBootTest
@ActiveProfiles("test")
public class ClubControllerTest {
	@Autowired
	private ClubController controller;

	@MockBean
	private ClubService service;

	//create
	@Test
	void testCreateClub() {
		//GIVEN
		Club actual = new Club("Team Birch");
		ClubDTO clubDTO = new ClubDTO(2, "Team Birch");
		//WHEN
		Mockito.when(service.createClub(actual)).thenReturn(clubDTO);
		//THEN
		assertThat(this.controller.createClub(actual)).isEqualTo(clubDTO);
		Mockito.verify(this.service, Mockito.times(1)).createClub(actual);
	}
	
	//read
	@Test
	void testReadClubs() {
		//GIVEN
		ClubDTO currentDTO = new ClubDTO(1, "Team Birch");
		List<ClubDTO> clubsDTOs = List.of(currentDTO);
		//WHEN
		Mockito.when(this.service.readClubs()).thenReturn(clubsDTOs);
		//THEN
		assertThat(this.controller.readClubs()).isEqualTo(clubsDTOs);
		Mockito.verify(this.service, Mockito.times(1)).readClubs();

	}
	
	//update
	@Test
	void testUpdateClub() {
		//GIVEN
		Integer actualId = 1;
		Club actual = new Club("Team Birch");
		ClubDTO updatedClubDTO = new ClubDTO(actualId, "Team Birch");
		//WHEN
		Mockito.when(this.service.updateClub(actualId, actual)).thenReturn(updatedClubDTO);
		//THEN
		assertThat(this.controller.updateClub(actual, actualId)).isEqualTo(updatedClubDTO);
		Mockito.verify(this.service, Mockito.times(1)).updateClub(actualId, actual);
	}

	//delete
	@Test
	void testDeleteClub() {
		//GIVEN
		Integer actualId = 1;
		//WHEN
		Mockito.when(this.service.deleteClub(actualId)).thenReturn(true);
		//THEN
		assertThat(this.controller.deleteClub(actualId)).isEqualTo(true);
		Mockito.verify(this.service, Mockito.times(1)).deleteClub(actualId);
	}
	
	//read by id
	@Test
	void testReadById() {
		//GIVEN
		Integer actualId = 1;
		Club actual = new Club("Team Birch");
		ClubDTO clubDTO = new ClubDTO(1, "Team Birch");
		//WHEN
		Mockito.when(this.service.readById(1)).thenReturn(clubDTO);
		//THEN
		assertThat(this.controller.readById(1)).isEqualTo(clubDTO);
		Mockito.verify(this.service, Mockito.times(1)).readById(1);
	}
}

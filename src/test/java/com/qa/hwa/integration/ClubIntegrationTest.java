package com.qa.hwa.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hwa.domain.Club;
import com.qa.hwa.domain.Place;
import com.qa.hwa.dto.ClubDTO;
import com.qa.hwa.dto.PlaceDTO;
import com.qa.hwa.repo.ClubRepo;
import com.qa.hwa.repo.PlaceRepo;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:swimr-schema.sql", "classpath:swimr-data.sql"}, 
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ClubIntegrationTest {
	@Autowired
	private MockMvc mvc; //allows to send mock requests
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ClubRepo repo;
	
	//create
	@Test
	void testCreateClub() throws Exception {
		Club testClub = new Club("The Birch Team");
		String testClubAsJSON = this.mapper.writeValueAsString(testClub);
		Club savedClub = new Club(2, "The Birch Team");
		String savedClubAsJSON = this.mapper.writeValueAsString(savedClub);
		this.mvc.perform(post("/clubs/create").content(testClubAsJSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(savedClubAsJSON));
	}
	
	//read
	@Test
	void testReadClubs() throws Exception {
		PlaceDTO place = new PlaceDTO(1, "Hackney Pool", "E12 6LB");
		List<PlaceDTO> placesDTOs = List.of(place);
		ClubDTO clubDTO = new ClubDTO(1, "Team Birch", placesDTOs);;
		List<ClubDTO> clubsDTOs = List.of(clubDTO);
		String testClubAsJSONArray = this.mapper.writeValueAsString(clubsDTOs);
		this.mvc.perform(get("/clubs/readAll"))
		.andExpect(status().isOk())
		.andExpect(content().json(testClubAsJSONArray));
	}
	
	//update
	@Test
	void testUpdateClub() throws Exception {
		Club toUpdateClub = new Club(1, "Team Elm");
		String toUpdateClubAsJSON =  this.mapper.writeValueAsString(toUpdateClub);
		PlaceDTO place = new PlaceDTO(1, "Hackney Pool", "E12 6LB");
		List<PlaceDTO> placesDTOs = List.of(place);
		ClubDTO updatedClubDTO = new ClubDTO(1, "Team Elm", placesDTOs);
		String updatedClubAsJSON = this.mapper.writeValueAsString(updatedClubDTO);
		this.mvc.perform(put("/clubs/update/1").content(toUpdateClubAsJSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(updatedClubAsJSON));
	}
	
	//delete
	@Test
	void testDeleteClub() throws Exception {
		assertThat(repo.existsById(1));
		this.mvc.perform(delete("/clubs/delete/1")).andExpect(status().isOk());
		assertThat(!(repo.existsById(1)));
	}
	
	//read by id
	@Test
	void testReadByID() throws Exception {
		PlaceDTO place = new PlaceDTO(1, "Hackney Pool", "E12 6LB");
		List<PlaceDTO> placesDTOs = List.of(place);
		ClubDTO testClubDTO = new ClubDTO(1, "Team Birch", placesDTOs);
		String testClubAsJSONArray = this.mapper.writeValueAsString(testClubDTO);
		this.mvc.perform(get("/clubs/read/1"))
		.andExpect(status().isOk())
		.andExpect(content().json(testClubAsJSONArray));
	}
}





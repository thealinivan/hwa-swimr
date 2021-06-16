package com.example.hwa.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.example.hwa.domain.Club;
import com.example.hwa.domain.Place;
import com.example.hwa.repo.ClubRepo;
import com.example.hwa.repo.PlaceRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		Club testClub = new Club(1, "Team Birch");
		List<Club> clubs = List.of(testClub);
		String testClubAsJSONArray = this.mapper.writeValueAsString(clubs);
		this.mvc.perform(get("/clubs/readAll"))
		.andExpect(status().isOk())
		.andExpect(content().json(testClubAsJSONArray));
	}
	
	//update
	@Test
	void testUpdateClub() throws Exception {
		Club updateClub = new Club(1, "Team Elm");
		String updateClubAsJSON = this.mapper.writeValueAsString(updateClub);
		this.mvc.perform(put("/clubs/update/1"))
		.andExpect(status().isOk())
		.andExpect(content().json(updateClubAsJSON));
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
		Club testClub = new Club(1, "Team Birch");
		String testClubAsJSONArray = this.mapper.writeValueAsString(testClub);
		this.mvc.perform(get("/clubs/readAll/1"))
		.andExpect(status().isOk())
		.andExpect(content().json(testClubAsJSONArray));
	}
}



package com.example.hwa.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.RequestBuilder;
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


import com.example.hwa.domain.Place;
import com.example.hwa.dto.PlaceDTO;
import com.example.hwa.repo.PlaceRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:swimr-schema.sql", "classpath:swimr-data.sql"}, 
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PlaceIntegrationTest {
	@Autowired
	private MockMvc mvc; //allows to send mock requests
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private PlaceRepo repo;
	
	//create
	@Test
	void testCreatePlace() throws Exception {
		Place testPlace = new Place("Leytonstone Pool", "E11 3DW");
		String testPlaceAsJSON = this.mapper.writeValueAsString(testPlace);
		PlaceDTO savedPlaceDTO = new PlaceDTO(2, "Leytonstone Pool", "E11 3DW");
		String savedPlaceAsJSON = this.mapper.writeValueAsString(savedPlaceDTO);
		this.mvc.perform(post("/places/create").content(testPlaceAsJSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(savedPlaceAsJSON));
	}
	
	
	//read
	@Test
	void testReadPlaces() throws Exception {
		PlaceDTO testPlaceDTO = new PlaceDTO(1, "Hackney Pool", "E12 6LB");
		List<PlaceDTO> placesDTOs = List.of(testPlaceDTO);
		String testPlaceAsJSONArray = this.mapper.writeValueAsString(placesDTOs);
		this.mvc.perform(get("/places/readAll"))
		.andExpect(status().isOk())
		.andExpect(content().json(testPlaceAsJSONArray));
	}
	
	
	//update
	@Test
	void testUpdatePlace() throws Exception {
		Place testPlace = new Place(1, "Leytonstone Pool", "E12 6LB");
		String testPlaceAsJSON = this.mapper.writeValueAsString(testPlace);
		Place updatedPlaceDTO = new Place(1, "Leytonstone Pool", "E12 6LB");
		String updatedPlaceAsJSON = this.mapper.writeValueAsString(updatedPlaceDTO);
		this.mvc.perform(put("/places/update/1").content(testPlaceAsJSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(updatedPlaceAsJSON));
	}
	
	//delete
	@Test
	void testDeletePlace() throws Exception {
		assertThat(repo.existsById(1));
		this.mvc.perform(delete("/places/delete/1")).andExpect(status().isOk());
		assertThat(!(repo.existsById(1)));
	}
	
	
	
	
	
}





























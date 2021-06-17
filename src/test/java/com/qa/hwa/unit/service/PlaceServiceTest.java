package com.qa.hwa.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hwa.domain.Club;
import com.qa.hwa.domain.Place;
import com.qa.hwa.dto.PlaceDTO;
import com.qa.hwa.repo.PlaceRepo;
import com.qa.hwa.service.PlaceService;
import com.qa.hwa.utils.PlaceMapper;

@SpringBootTest
@ActiveProfiles("test")
public class PlaceServiceTest {
	@Autowired
	private PlaceService service;
	
	@MockBean
	private PlaceRepo repo;
		
	@Autowired
	private PlaceMapper mapper;
	
	
	//create
	@Test
	void testCreatePlace() {
		//GIVEN
		Place saved = new Place(2, "Hackney Pool", "E12 6LB", new Club());
		PlaceDTO savedPlaceDTO = new PlaceDTO(2, "Hackney Pool", "E12 6LB");
		//WHEN
		Mockito.when(this.repo.save(saved)).thenReturn(saved);
		//THEN
		assertThat(this.service.createPlace(saved)).isEqualTo(savedPlaceDTO);
	}
		
	//read
	@Test
	void testReadPlaces() {
		//GIVEN
		Place currentPlace = new Place(1, "Hackney Pool", "E12 6LB", new Club());
		List<Place> places = List.of(currentPlace);
		PlaceDTO currentPlaceDTO = new PlaceDTO(1, "Hackney Pool", "E12 6LB");
        List<PlaceDTO> placesDTOs = List.of(currentPlaceDTO);
		//WHEN
		Mockito.when(this.repo.findAll()).thenReturn(places);
		//THEN
		assertThat(this.service.readPlaces()).isEqualTo(placesDTOs);
	}
	
	//update
	@Test
	void testUpdatePlace() {
		//Resources
		Place currentPlace = new Place(1, "Hackney Pool", "E12 6LB");
		Place updatedPlace = new Place(1, "Eastham Pool", "E11 3DW");
		PlaceDTO updatedPlaceDTO = new PlaceDTO(1, "Eastham Pool", "E11 3DW");
		//Rule
		Mockito.when(this.repo.findById(1)).thenReturn(Optional.of(currentPlace));
		Mockito.when(this.repo.save(currentPlace)).thenReturn(updatedPlace);
		//Actions
		PlaceDTO updateResult = this.service.updatePlace(1, updatedPlace);
		//Assertions
		assertThat(updateResult).usingRecursiveComparison().isEqualTo(updatedPlaceDTO);
	}
	
	//delete
	@Test
	void testDeletePlace() {
		//GIVEN
		Integer actualId = 1;
		//WHEN
		Mockito.when(this.repo.existsById(actualId)).thenReturn(false);
		//THEN
		assertThat(this.service.deletePlace(actualId)).isEqualTo(!false);
	}

}

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
import com.example.hwa.dto.PlaceDTO;
import com.example.hwa.repo.PlaceRepo;
import com.example.hwa.service.PlaceService;

@SpringBootTest
@ActiveProfiles("test")
public class PlaceServiceTest {
	@Autowired
	private PlaceService service;
	
	@MockBean
	private PlaceRepo repo;
		
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
		//GIVEN
		Place currentPlace = new Place(1, "Hackney Pool", "E12 6LB");
		Place updatedPlace = new Place(1, "Eastham Pool", "E11 3DW");
		PlaceDTO updatedPlaceDTO = new PlaceDTO(1, "Eastham Pool", "E11 3DW");
		//WHEN
		Mockito.when(this.repo.findById(1)).thenReturn(Optional.of(currentPlace));
		Mockito.when(this.repo.save(updatedPlace)).thenReturn(updatedPlace);
		//THEN
		assertThat(this.service.updatePlace(1, updatedPlace))
		.usingRecursiveComparison().isEqualTo(updatedPlaceDTO);
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

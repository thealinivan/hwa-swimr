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
		Place actual = new Place("Hackney Pool", "E12 6LB");
		Place saved = new Place(2, "Hackney Pool", "E12 6LB");
		PlaceDTO savedPlaceDTO = new PlaceDTO(2, "Hackney Pool", "E12 6LB");
		//WHEN
		Mockito.when(this.repo.save(actual)).thenReturn(saved);
		//THEN
		assertThat(this.service.createPlace(actual)).isEqualTo(savedPlaceDTO);
	}
		
	//read
	@Test
	void testReadPlaces() {
		//GIVEN
		Place currentPlace = new Place(1, "Hackney Pool", "E12 6LB");
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
		Integer actualId = 1;
		Place actual = new Place("Eastham Pool", "E12 6LB");
		Place currentPlace = new Place(1, "Hackney Pool", "E12 6LB");
		Place updatedPlace = new Place(actualId, "Eastham Pool", "E11 3DW");
		PlaceDTO updatedPlaceDTO = new PlaceDTO(actualId, "Eastham Pool", "E11 3DW");
		//WHEN
		Mockito.when(this.repo.findById(actualId)).thenReturn(Optional.of(currentPlace));
		Mockito.when(this.repo.save(updatedPlace)).thenReturn(updatedPlace);
		//THEN
		assertThat(this.service.updatePlace(actualId, actual)).isEqualTo(updatedPlaceDTO);
		//might not need verification
		Mockito.verify(this.repo, Mockito.times(1)).findById(actualId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPlace);
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

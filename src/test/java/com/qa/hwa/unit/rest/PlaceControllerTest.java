package com.qa.hwa.unit.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hwa.domain.Place;
import com.qa.hwa.dto.PlaceDTO;
import com.qa.hwa.rest.PlaceController;
import com.qa.hwa.service.PlaceService;

@SpringBootTest
@ActiveProfiles("test")
public class PlaceControllerTest {
	@Autowired
	private PlaceController controller;

	@MockBean
	private PlaceService service;

	//create
	@Test
	void testCreatePlace() {
		//GIVEN
		Place actual = new Place("Hackney Pool", "E12 6LB");
		PlaceDTO placeDTO = new PlaceDTO(2, "Hackney Pool", "E12 6LB");
		//WHEN
		Mockito.when(service.createPlace(actual)).thenReturn(placeDTO);
		//THEN
		assertThat(this.controller.createPlace(actual)).isEqualTo(placeDTO);
		Mockito.verify(this.service, Mockito.times(1)).createPlace(actual);
	}
	
	//read
	@Test
	void testReadPlaces() {
		//GIVEN
		PlaceDTO currentDTO = new PlaceDTO(1, "Hackney Pool","E12 6LB");
		List<PlaceDTO> placesDTOs = List.of(currentDTO);
		//WHEN
		Mockito.when(this.service.readPlaces()).thenReturn(placesDTOs);
		//THEN
		assertThat(this.controller.readPlaces()).isEqualTo(placesDTOs);
		Mockito.verify(this.service, Mockito.times(1)).readPlaces();

	}
	
	//update
	@Test
	void testUpdatePlace() {
		//GIVEN
		Integer actualId = 1;
		Place actual = new Place("Hackney Pool", "E12 6LB");;
		PlaceDTO updatedPlaceDTO = new PlaceDTO(actualId, "Hackney Pool", "E12 6LB");
		//WHEN
		Mockito.when(this.service.updatePlace(actualId, actual)).thenReturn(updatedPlaceDTO);
		//THEN
		assertThat(this.controller.updatePlace(actual, actualId)).isEqualTo(updatedPlaceDTO);
		Mockito.verify(this.service, Mockito.times(1)).updatePlace(actualId, actual);
	}

	//delete
	@Test
	void testDeletePlace() {
		//GIVEN
		Integer actualId = 1;
		//WHEN
		Mockito.when(this.service.deletePlace(actualId)).thenReturn(true);
		//THEN
		assertThat(this.controller.deletePlace(actualId)).isEqualTo(true);
		Mockito.verify(this.service, Mockito.times(1)).deletePlace(actualId);
	}

}

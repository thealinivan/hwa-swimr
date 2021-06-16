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

import com.example.hwa.domain.Place;
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
	void testCreate() {
		//GIVEN
		Place place = new Place("Hackney Pool", "E12 6LB");
		//WHEN
		Mockito.when(this.repo.save(place)).thenReturn(place);
		//THEN
		assertThat(this.service.createPlace(place)).isEqualTo(place);
		Mockito.verify(this.repo, Mockito.times(1)).save(place);
	}
		
	//read
	@Test
	void testReadAll() {
		//GIVEN
		Place place = new Place("Hackney Pool", "E12 6LB");
        List<Place> places = List.of(place);
		//WHEN
		Mockito.when(this.repo.save(place)).thenReturn(place);
		Mockito.when(this.repo.findAll()).thenReturn(places);
		//THEN
		assertThat(this.service.readPlaces()).isEqualTo(places);
		Mockito.verify(this.repo, Mockito.times(1)).save(place);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	
	}
	
	//update
	@Test
	void testUpdate() {
		//GIVEN
		Integer testId = 1;
		Place testData = new Place("Eastham Pool", "E12 6LB");
		Place currentPlace = new Place(1, "Hackney Pool", "E12 6LB");
		Place updatedPlace = new Place(testId, "Eastham Pool", "E11 3DW");
		//WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(currentPlace));
		Mockito.when(this.repo.save(updatedPlace)).thenReturn(updatedPlace);
		//THEN
		assertThat(this.service.updatePlace(testId, testData)).isEqualTo(updatedPlace);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPlace);
	}
	
	//delete
	@Test
	void testDelete() {
		//GIVEN
		Integer testId = 1;
		//WHEN
		Mockito.when(this.repo.existsById(testId)).thenReturn(false);
		//THEN
		assertThat(this.service.deletePlace(testId)).isEqualTo(!false);
	
		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}

}

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;

@RestController
@RequestMapping("/api")
public class CityController {
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CountryRepository countryRepository;
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCity() {
		try {
			List<City> cities = cityRepository.findAll();
			
			if(cities == null) {
				return new ResponseEntity<>(null ,HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(cities, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/country/{countryId}/city")
	public ResponseEntity<City> createCity(@PathVariable(value = "countryId") long countryId, @RequestBody City city) {
		Optional<City> createCity = countryRepository.findById(countryId).map(country -> {
			city.setCountry(country);
			return cityRepository.save(city);
		});
		
		if(createCity == null) {
			 new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(createCity.get(), HttpStatus.CREATED);
	}
	
	@PutMapping("/city/{id}")
	public ResponseEntity<City> UpdateCity(@PathVariable(value = "id") long id, @RequestBody City requestCity) {
		Optional<City> updateCity = cityRepository.findById(id).map(city -> {
			City newCity = city;
			newCity.setName(requestCity.getName());
			
			return cityRepository.save(newCity);
		});
		
		return new ResponseEntity<>(updateCity.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/city/{id}")
	public ResponseEntity<HttpStatus> deletecity(@PathVariable("id") long id) {
		cityRepository.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/country/{countryId}/city")
    public ResponseEntity<List<City>> deleteAllCityOfCountry(@PathVariable(value = "countryId") Long countryId) {
	    if (!countryRepository.existsById(countryId)) {
	    	return new ResponseEntity<>(null ,HttpStatus.NO_CONTENT);
	    }
	    cityRepository.deleteByCountryId(countryId);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
}

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

import com.example.demo.repository.CountryRepository;
import com.example.demo.models.Country;


@RestController
@RequestMapping("/api")
public class CountryController {
	@Autowired
	CountryRepository countryRepository;
	
	@GetMapping("/countries")
	public ResponseEntity<List<Country>> getAllCountries() {
		try {
			List<Country> countries = countryRepository.findAll();
			
			if(countries == null) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(countries, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/country/{name}")
	public ResponseEntity<List<Country>> getCountriesByName(@PathVariable("name") String name) {
		try {
			List<Country> countries = countryRepository.findCountryByName(name);
			
			if(countries == null) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(countries, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/country")
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		try {
			Country createCountry = countryRepository.save(new Country(country.getName(), country.getAd()));
			
			return new ResponseEntity<>(createCountry, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/country/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable("id") long id, @RequestBody Country country) {
		try {
			Optional<Country> isExistCountry = countryRepository.findById(id);
			
			if(isExistCountry.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			Country updateCountry = isExistCountry.get();
			updateCountry.setName(country.getName());
			updateCountry.setAd(country.getAd());
			
			return new ResponseEntity<>(countryRepository.save(updateCountry), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/country/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable("id") long id) {
		try {
			countryRepository.deleteById(id);	
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

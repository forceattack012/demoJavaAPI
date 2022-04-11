package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByCountryId(long countryId);
	
	@Transactional
	void deleteByCountryId(long cityId);
}

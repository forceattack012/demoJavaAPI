package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	List<Country> findCountryByName(String name);
}

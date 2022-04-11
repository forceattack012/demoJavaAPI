package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;

@SpringBootApplication
public class DemoJavaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJavaAppApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(CountryRepository countryRepo, CityRepository cityRepo) {
		
		return args -> {
			try {
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			countryRepo.findAll().forEach(System.out::println);
			cityRepo.findAll().forEach(System.out::println);
		};
	}

}

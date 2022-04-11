package com.example.demo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.repository.CountryRepository;
import com.example.demo.models.Country;

@SpringBootApplication
public class DemoJavaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJavaAppApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(CountryRepository countryRepo) {
		
		return args -> {
			try {
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			countryRepo.findAll().forEach(System.out::println);
		};
	}

}

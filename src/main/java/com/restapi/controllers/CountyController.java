package com.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.models.Country;
import com.restapi.repos.CountryRepo;

@RestController
@RequestMapping("/v1/countries")
public class CountyController {
	
	@Autowired
	CountryRepo countryRepo;
	
	@GetMapping("/{id}")
	public Country getCountry(@PathVariable int id) {
		Country country = new Country();
		return country;
	}
	
	@GetMapping("/")
	public List<Country> getAllCountries(){
		return null;
	}
	
	@PostMapping("/")
	public void addCountry(@PathVariable Country country) {
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteCountry(@PathVariable int id) {
		
	}
	
	@PutMapping("/")
	public void updateCountry(@PathVariable Country country) {
		
	}

}

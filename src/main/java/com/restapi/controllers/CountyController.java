package com.restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<Country> countries = countryRepo.findAll();
		return countries;
	}
	
	@PostMapping("/")
	public void addCountry(@RequestBody Country country) {
		countryRepo.save(country);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCountry(@PathVariable int id) {
		countryRepo.deleteById(id);
	}
	
	@PutMapping("/")
	public void updateCountry(@RequestBody Country country) {
		countryRepo.save(country);
	}

}

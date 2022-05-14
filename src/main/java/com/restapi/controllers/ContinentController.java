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

import com.restapi.models.Continent;
import com.restapi.repos.ContinentRepo;

@RestController
@RequestMapping("/v1/continents")

public class ContinentController {
	
	@Autowired
	ContinentRepo continentRepo;
	
	@GetMapping("/{id}")
	public Continent getContinent(@PathVariable int id) {
		Continent continent = new Continent();
		return continent;
	}
	
	@GetMapping("/")
	public List<Continent> getAllContinents(){
		return null;
	}
	
	@PostMapping("/")
	public void addContinent(@PathVariable Continent continent) {
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteContinent(@PathVariable int id) {
		
	}
	
	@PutMapping("/")
	public void updateContinent(@PathVariable Continent country) {
		
	}
}
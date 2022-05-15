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

import com.restapi.models.City;
import com.restapi.repos.CityRepo;

@RestController
@RequestMapping("/v1/cities")
public class CityController {
	
	@Autowired
	CityRepo cityRepo;
	
	@GetMapping("/{id}")
	public City getCity(@PathVariable int id) {
		City city = new City();
		return city;
	}
	
	@GetMapping("/")
	public List<City> getAllCity(){
		List<City> list = cityRepo.findAll();
		return list;
	}
	
	@PostMapping("/")
	public void addCity(@RequestBody City city) {
		cityRepo.save(city);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCity(@PathVariable int id) {
		cityRepo.deleteById(id);
	}
	
	@PutMapping("/")
	public void updateCity(@RequestBody City city) {
		cityRepo.save(city);
	}

}

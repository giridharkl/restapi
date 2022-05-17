package com.restapi.controllers;

import java.util.List;

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

import com.restapi.models.ApiError;
import com.restapi.models.ApiSuccess;
import com.restapi.models.City;
import com.restapi.repos.CityRepo;

@RestController
@RequestMapping("/v1/cities")
public class CityController {
	
	@Autowired
	CityRepo cityRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCity(@PathVariable int id) {
		City city;
		try {
			city = cityRepo.findById(id).get();
			return new ResponseEntity<Object>(city, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllCity(){
		List<City> list;
		try {
			list = cityRepo.findAll();
			return new ResponseEntity<Object>(list, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addCity(@RequestBody City city) {
		try {
			cityRepo.save(city);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Added"), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCity(@PathVariable int id) {
		try {
			cityRepo.deleteById(id);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Deleted"), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateCity(@RequestBody City city) {
		try {
			cityRepo.save(city);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Updated"), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/country/{id}")
	public ResponseEntity<Object> getCitiesByCountryId(@PathVariable int id){
		try {
			cityRepo.findByContinentId(id);
			return null;
		} catch(Exception e){
			return null;
		}
	}
	
	@GetMapping("/continent/{id}")
	public ResponseEntity<Object> getCitiesByContinentId(@PathVariable int id){
		try {
			cityRepo.findByCountryId(id);
			return null;
		} catch(Exception e){
			return null;
		}
	}

}

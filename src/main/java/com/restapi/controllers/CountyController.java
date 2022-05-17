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
import com.restapi.models.Country;
import com.restapi.repos.CountryRepo;

@RestController
@RequestMapping("/v1/countries")
public class CountyController {
	
	@Autowired
	CountryRepo countryRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCountry(@PathVariable int id) {
		Country country;
		try {
			country = countryRepo.findById(id).get();
			return new ResponseEntity<Object>(country, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllCountries(){
		List<Country> countries;
		try {
			countries = countryRepo.findAll();
			return new ResponseEntity<Object>(countries, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addCountry(@RequestBody Country country) {
		try {
			countryRepo.save(country);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Added"), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCountry(@PathVariable int id) {
		try {
			countryRepo.deleteById(id);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Deleted"), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateCountry(@RequestBody Country country) {
		try {
			countryRepo.save(country);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Updated"), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}

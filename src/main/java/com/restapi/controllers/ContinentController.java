package com.restapi.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.restapi.models.Continent;
import com.restapi.repos.ContinentRepo;

@RestController
@RequestMapping("/v1/continents")

public class ContinentController {
	
	@Autowired
	ContinentRepo continentRepo;
	
	private static Logger log = LoggerFactory.getLogger(ContinentController.class);

	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getContinent(@PathVariable int id) {
		Continent continent = null;
		try {
			continent = continentRepo.findById(id).get();
			return new ResponseEntity<Object>(continent, HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllContinents(){
		List<Continent> list = null;
		try {
			list = continentRepo.findAll();
			return new ResponseEntity<Object>(list, HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addContinent(@RequestBody Continent continent) {
		try {
			continentRepo.save(continent);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Added"), HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteContinent(@PathVariable int id) {
		try {
			continentRepo.deleteById(id);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Deleted"), HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateContinent(@RequestBody Continent continent) {
		try {
			continentRepo.save(continent);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Updated"), HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

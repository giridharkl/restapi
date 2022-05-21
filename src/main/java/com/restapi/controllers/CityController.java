package com.restapi.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.restapi.models.City;
import com.restapi.models.CityModel;
import com.restapi.models.Continent;
import com.restapi.models.Country;
import com.restapi.repos.CityRepo;
import com.restapi.repos.ContinentRepo;
import com.restapi.repos.CountryRepo;

@RestController
@RequestMapping("/v1/cities")
public class CityController {
	
	@Autowired
	CityRepo cityRepo;
	
	@Autowired
	ContinentRepo continentRepo;
	
	@Autowired
	CountryRepo countryRepo;
	
	private static Logger log = LoggerFactory.getLogger(CityController.class);
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCity(@PathVariable int id) {
		City city;
		CityModel cityModel;
		try {
			city = cityRepo.findById(id).get();
			cityModel = getCityModel(city);
			return new ResponseEntity<Object>(cityModel, HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> getAllCity(){
		List<City> list;
		List<CityModel> cityModelList = null;
		try {
			list = cityRepo.findAll();
			if(list != null && list.size() > 0) {
				cityModelList = new ArrayList<CityModel>();
				for(Iterator<City> i = list.iterator(); i.hasNext();) {
					City c = i.next();
					CityModel cm = getCityModel(c);
					cityModelList.add(cm);
				}
			}
			return new ResponseEntity<Object>(cityModelList, HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> addCity(@RequestBody City city) {
		try {
			Country country;
			Continent continent;
			try {
				country = countryRepo.findById(city.getCountryId()).get();
			} catch (Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, "Country " + e.getMessage()), HttpStatus.NOT_FOUND);
			}
			
			try {
				continent = continentRepo.findById(city.getContinentId()).get();
			} catch (Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, "Continent " + e.getMessage()), HttpStatus.NOT_FOUND);
			}
			cityRepo.save(city);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Added"), HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCity(@PathVariable int id) {
		try {
			cityRepo.deleteById(id);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Deleted"), HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> updateCity(@RequestBody City city) {
		try {
			cityRepo.save(city);
			return new ResponseEntity<Object>(new ApiSuccess(HttpStatus.OK,"Updated"), HttpStatus.FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/country/{id}")
	public ResponseEntity<Object> getCitiesByCountryId(@PathVariable int id){
		List<CityModel> cityModelList = null;
		try {
			List<City> cities = cityRepo.findByCountryId(id);
			if(cities != null && cities.size() > 0) {
				cityModelList = new ArrayList<CityModel>();
				for(Iterator<City> i = cities.iterator(); i.hasNext();) {
					City c = i.next();
					CityModel cm = getCityModel(c);
					cityModelList.add(cm);
				}
			}
			return new ResponseEntity<Object>(cityModelList, HttpStatus.FOUND);
		} catch(Exception e){
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/continent/{id}")
	public ResponseEntity<Object> getCitiesByContinentId(@PathVariable int id){
		List<CityModel> cityModelList = null;
		try {
			List<City> cities = cityRepo.findByContinentId(id);
			if(cities != null && cities.size() > 0) {
				cityModelList = new ArrayList<CityModel>();
				for(Iterator<City> i = cities.iterator(); i.hasNext();) {
					City c = i.next();
					CityModel cm = getCityModel(c);
					cityModelList.add(cm);
				}
			}
			return new ResponseEntity<Object>(cityModelList, HttpStatus.FOUND);
		} catch(Exception e){
			log.error(e.getMessage());
			return new ResponseEntity<Object>(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	private CityModel getCityModel(City city) {
		CityModel cityModel = null;
		Country country;
		Continent continent;
		try {
			if(city != null) {
				continent = continentRepo.findById(city.getContinentId()).get();
				country = countryRepo.findById(city.getCountryId()).get();
				cityModel = new CityModel();
				cityModel.setId(city.getId());
				cityModel.setName(city.getName());
				cityModel.setContinent(continent);
				cityModel.setCountry(country);
			}
		}
		catch(Exception e) {
			log.error(e.getMessage());
		}
		finally {
			return cityModel;
		}
	}
}

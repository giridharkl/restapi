package com.restapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.models.City;

public interface CityRepo extends JpaRepository<City, Integer> {

	List<City> findByContinentId(int id);

	List<City> findByCountryId(int id);

}

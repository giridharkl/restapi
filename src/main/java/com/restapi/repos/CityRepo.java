package com.restapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.models.City;

public interface CityRepo extends JpaRepository<City, Integer> {

}

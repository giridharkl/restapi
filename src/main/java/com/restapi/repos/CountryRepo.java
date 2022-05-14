package com.restapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.models.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {

}

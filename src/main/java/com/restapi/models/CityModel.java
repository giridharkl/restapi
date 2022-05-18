package com.restapi.models;

public class CityModel {
	
	private int id;
	private String name;
	private Country country;
	private Continent continent;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Country getCountry() {
		return country;
	}
	public Continent getContinent() {
		return continent;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
}

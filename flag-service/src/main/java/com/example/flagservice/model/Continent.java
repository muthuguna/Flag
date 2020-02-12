package com.example.flagservice.model;

import java.util.List;

/**
 * Continent model class with list of Country objects
 */
public class Continent {
	private String continent;

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	private List<Country> countries;

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

}

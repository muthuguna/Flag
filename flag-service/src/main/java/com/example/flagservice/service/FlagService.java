package com.example.flagservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.flagservice.model.Continent;
import com.example.flagservice.model.Country;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * FlagService a service layer class that provides methods to get the flag data
 * map from JSON file, list of countries for a given continent and flag for a
 * given country.
 */
@Service
public class FlagService {

	/**
	 * Gets the flags data map from continents.json 
	 * @return Map<String,List<Country>> 
	 * @param 
	 * @throws
	 */
	public Map<String, List<Country>> getFlagDataMap() {

		Map<String, List<Country>> flagDataMap = null;
		ObjectMapper objectMapper = new ObjectMapper();
		List<Continent> continents = null;

		try {
			// Get the flag data map with continent name as key and List of countries object
			// as value
			continents = objectMapper.readValue(ResourceUtils.getFile("classpath:continents.json"),
					new TypeReference<List<Continent>>() {
					});

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (continents != null) {
			// Sort the continent in ascending order to display
			flagDataMap = continents.stream().collect(
					Collectors.toMap(Continent::getContinent, Continent::getCountries, (v1, v2) -> v1, TreeMap::new));
		}
		return flagDataMap;
	}

	/**
	 * Gets the list of countries for a given continent 
	 * @return List<Country> 
	 * @param String
	 * @throws
	 */
	public List<Country> getCountries(String continent) {
		List<Country> cntrys = null;

		// In case if input continent name is not in Camel Case
		StringBuilder camelCaseContinentName = new StringBuilder();
		if (continent != null) {
			for (String oneString : continent.split(" ")) {
				camelCaseContinentName.append(oneString.substring(0, 1).toUpperCase());
				camelCaseContinentName.append(oneString.substring(1)).append(" ");
			}
		}

		// Get the countries list for a given continent
		Map<String, List<Country>> flagDataMap = getFlagDataMap();
		if (flagDataMap != null && flagDataMap.size() > 0) {
			cntrys = flagDataMap.get(camelCaseContinentName.toString().trim());
		}

		// Sort the countries list in ascending order to display
		if (cntrys != null && cntrys.size() > 0) {
			cntrys = cntrys.stream().sorted(Comparator.comparing(Country::getName)).collect(Collectors.toList());
		}
		return cntrys;
	}

	/**
	 * Gets the flag in list as single entity for a given country 
	 * @return List<String> 
	 * @param String
	 * @throws
	 */
	public List<String> getFlag(String country) {

		List<String> flag = new ArrayList<String>();

		// In case if input country name is not in Camel Case
		StringBuilder camelCaseCountryName = new StringBuilder();
		if (country != null) {
			for (String oneString : country.split(" ")) {
				camelCaseCountryName.append(oneString.substring(0, 1).toUpperCase());
				camelCaseCountryName.append(oneString.substring(1)).append(" ");
			}
		}

		// Get the flag for a given country from the flag data map
		Map<String, List<Country>> flagDataMap = getFlagDataMap();
		if (flagDataMap != null && flagDataMap.size() > 0) {
			DataMapLoop: for (List<Country> countries : flagDataMap.values()) {
				if (countries != null && countries.size() > 0) {
					for (Country cntry : countries) {
						if (camelCaseCountryName.toString().trim().equals(cntry.getName())) {
							flag.add(cntry.getFlag().trim());
							break DataMapLoop;
						}
					}
				}
			}
		}
		return flag;
	}
}

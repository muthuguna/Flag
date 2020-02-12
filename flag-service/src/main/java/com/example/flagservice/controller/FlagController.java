package com.example.flagservice.controller;

import java.net.URLDecoder;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eample.flagservice.constants.Constant;
import com.example.flagservice.model.Country;
import com.example.flagservice.service.DBAuditService;
import com.example.flagservice.service.FlagService;

/**
 * FlagController contains methods for getting all data from the JSON file, list of all continents,
 * list of countries for a given continent and flag for a given country.
 */
@RestController
@RequestMapping("/rest/flagService")
public class FlagController {

	private static final Logger logger = LogManager.getLogger(FlagController.class);

	@Autowired
	FlagService flagService;

	@Autowired
	DBAuditService dbAuditService;

	/**
	 * This method gets whole data map of Map<String, List<Country>>, key being continent and value 
	 * being the list of countries object.
	 * @return Map<String, List<Country>> 
	 * @param 
	 * @throws
	 */
	@GetMapping("")
	public Map<String, List<Country>> getAllData() {
		dbAuditService.performAudit(Constant.ALLDATA);
		return flagService.getFlagDataMap();
	}

	/**
	 * This method gets list of continents from the given JSON input file.
	 * @return Set<String> 
	 * @param 
	 * @throws
	 */
	@GetMapping("/continents")
	public Set<String> getAllContinents() {
		
		Set<String> continents = new TreeSet<String>();
		Instant start = Instant.now();
		
		Map<String, List<Country>> flagDataMap = flagService.getFlagDataMap();
		dbAuditService.performAudit(Constant.CONTINENTS);
		Instant end = Instant.now();
		logger.debug("Time taken to complete getAllContinents() " + Duration.between(start, end).toMillis());
		
		if(flagDataMap != null && flagDataMap.size() > 0) {
			continents = flagDataMap.keySet();
		}
		return continents;
		

	}

	/**
	 * This method gets list of countries for a given continent.
	 * @return List<Country> 
	 * @param String
	 * @throws Exception
	 */
	@GetMapping("/countries/{continent}")
	public List<Country> getCountries(@PathVariable("continent") String continent) throws Exception {
		continent = URLDecoder.decode(continent, "UTF-8");
		Instant start = Instant.now();

		List<Country> cntrys = flagService.getCountries(continent);
		dbAuditService.performAudit(Constant.COUNTRIES);
		
		Instant end = Instant.now();
		logger.debug("Time taken to complete getCountries() " + Duration.between(start, end).toMillis());
		
		return cntrys;
	}

	/**
	 * This method gets the flag for a given Country.
	 * @return List<String> 
	 * @param String
	 * @throws Exception
	 */
	@GetMapping("/flag/{country}")
	public List<String> getFlag(@PathVariable("country") String country) throws Exception {
		country = URLDecoder.decode(country, "UTF-8");
		Instant start = Instant.now();
		
		List<String> flag = flagService.getFlag(country);
		dbAuditService.performAudit(Constant.FLAGS);
		
		Instant end = Instant.now();
		logger.debug("Time taken to complete getFlag() " + Duration.between(start, end).toMillis());
		
		return flag;
	}

}

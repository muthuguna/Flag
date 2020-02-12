package com.example.flagservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * DBAuditService a service layer class that audits the user activity by
 * persisting in MySQL DB. It calls the mysql-db-service that is running as a
 * separate service component on http://localhost:8401/rest/db
 */
@Service
public class DBAuditService {

	@Autowired
	RestTemplate restTemplate;

	private static final Logger logger = LogManager.getLogger(DBAuditService.class);

	/**
	 * This method updates the count of respective event being called. The possible
	 * events could be ALLDATA, CONTINENTS, COUNTRIES and FLAGS
	 * List<Country>> @throws
	 */
	public ResponseEntity<Integer> performAudit(String event) {

		ResponseEntity<Integer> auditResponse = null;
		auditResponse = restTemplate.exchange("http://localhost:8401/rest/db/update/" + event, HttpMethod.POST, null,
				new ParameterizedTypeReference<Integer>() {
				});
		logger.debug("# hits for the call get " + event + " :" + auditResponse.getBody().toString());
		return auditResponse;
	}

}

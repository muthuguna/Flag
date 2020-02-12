package com.example.mysqldbservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysqldbservice.model.Audit;
import com.example.mysqldbservice.repository.AuditRepository;

/**
 * MysqlDBServiceResource is the controller class for persisting user activity in MySQL DB
 * This class provides method for updating event count in Audit table.
 */
@RestController
@RequestMapping("/rest/db")
public class MysqlDBServiceResource {
	@Autowired
	private AuditRepository auditRepository;

	@GetMapping("/get/{event}")
	public Integer getCount(@PathVariable("event") final String event) {

		return getAuditByEvent(event).getCount();
	}

	/**
	 * This method updates the current count of user activity event in the Audit table.
	 */
	@PostMapping("/update/{event}")
	public Integer update(@PathVariable("event") final String event) {
		int curCount = getAuditByEvent(event).getCount();
		Audit audit = getAuditByEvent(event);
		audit.setCount(curCount + 1);
		return auditRepository.save(audit).getCount();
	}

	/**
	 * This method returns the current count of user activity event from the Audit table.
	 */
	private Audit getAuditByEvent(String event) {
		return auditRepository.findByEvent(event);
	}

}

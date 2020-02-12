package com.example.mysqldbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysqldbservice.model.Audit;

public interface AuditRepository extends JpaRepository<Audit, Integer> {
	Audit findByEvent(String event);
}

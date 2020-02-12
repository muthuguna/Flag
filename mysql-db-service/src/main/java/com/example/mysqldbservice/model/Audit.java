package com.example.mysqldbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Audit is a model class for persisting user activity count in MySQL DB
 */
@Entity
@Table(name = "audit", catalog = "test")
public class Audit {

	@Id
//	    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "event")
	private String event;

	@Column(name = "count")
	private Integer count;
}

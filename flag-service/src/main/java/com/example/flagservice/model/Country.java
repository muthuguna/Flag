package com.example.flagservice.model;

/**
 * Country model class with country name and its flag
 */
public class Country {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	private String flag;

	@Override
	public String toString() {
		return name + " " + flag;
	}

}

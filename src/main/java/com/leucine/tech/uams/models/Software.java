package com.leucine.tech.uams.models;

public class Software {
	private int id;
	private String name;

	public Software(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

package com.bizleap.commons.domain.entity;
/*
Assignment 4
There are three persons and three companies (you can add more):
Alice Kim -- works for Apple
Bob Gilman -- works for IBM
John Mark -- works for Adobe

1) Write a java program that will read the above employees' first name, last name, age, title, salary,
 email, phone ( add more fields to above people) from a file as well as to read the company names, address,
  phone, email, CEO (make up some one for each company) from a second file and print out the company then 
  it's employee(s) for all companies.
2) Find out what commonality do the two entity classes have and reimplement it
 by using the inheritance features of Java. 
*/

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Entity {

	private long id;
	private String boId;

	public Entity() {

	}

	public Entity(long id){
		this.id=id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public Entity(String boId) {
		this.boId = boId;
	}

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boid) {
		this.boId = boid;
	}
	
	public boolean isEqual(String boId) {
		return this.boId.equals(boId);
	}
	
	public boolean sameBoId(Entity entity) {
		if(entity!=null)
			return this.getBoId().equals(entity.getBoId());
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Id ", getBoId())
				.toString(); 
	}
}

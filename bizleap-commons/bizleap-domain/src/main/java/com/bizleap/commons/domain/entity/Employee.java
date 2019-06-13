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
package com.bizleap.commons.domain.entity;

import java.util.StringTokenizer;

public class Employee extends Entity {

	private final static String DELI_METER = ",";
	private String boId, firstName, lastName, age, title, salary, email, phone;
	private static StringTokenizer st;
	private Company workFor;
	public Employee(String boId, String firstName, String lastName, String age, String title, String salary,
			String email, String phone) {
		super(boId, phone, email);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.title = title;
		this.salary = salary;

	}

	public Company getWorkFor() {
		return workFor;
	}
	
	public void setWorkFor(Company workFor) {
		this.workFor=workFor;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static Employee parseEmployee(String eachLine) {
		String boid = null, firstName = null, lastName = null, age = null, title = null, salary = null, email = null,
				phone = null,companyBoId=null;
		st = new StringTokenizer(eachLine, DELI_METER);
		while (st.hasMoreTokens()) {
			boid = st.nextToken();
			companyBoId=st.nextToken();
			firstName = st.nextToken();
			lastName = st.nextToken();
			age = st.nextToken();
			title = st.nextToken();
			salary = st.nextToken();
			email = st.nextToken();
			phone = st.nextToken();
			System.out.println(boid);
		}
		//Employee employee=new EmployeeBuilder().boId(boid).firstName(firstName).lastName(lastName).age(age).title(title)
			//	.salary(salary).email(email).phone(phone).buildEmployee();
		//employee.setWorkFor(new Company(companyBoId));
		return null;
	}

	public boolean checkEquality(String boId) {
		return getWorkFor().getBoId().equals(boId);
	}
	
	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}
}

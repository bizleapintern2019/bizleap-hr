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
package com.bizleap.domains.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Company extends Entity {

	private final static String DELI_METER = ",";
	private String boId, name, address, email, phone, ceo;
	private static StringTokenizer st;
	private List<Employee> employeeList;
	public Company(String boId) {
		super(boId);
	}
	
	public void setEmployeeList(Employee employee) {
		if(employeeList==null) {
			employeeList=new ArrayList<Employee>();
		}
		this.employeeList.add(employee);
	}
	
	public List<Employee> getEmployeeList(){
		return this.employeeList;
	}
	
	public boolean checkEquality(Company company) {
		
		return getBoId().equals(company.getBoId());
	}
	
	public Company(String boId, String name, String address, String email, String phone, String ceo) {
		super(boId, phone, email);
		this.name = name;
		this.address = address;
		this.ceo = ceo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Company parseCompany(String eachLine) {
		String boId = null, name = null, address = null, email = null, phone = null, ceo = null;
		st = new StringTokenizer(eachLine, DELI_METER);
		while (st.hasMoreTokens()) {
			boId = st.nextToken();
			name = st.nextToken();
			address = st.nextToken();
			email = st.nextToken();
			phone = st.nextToken();
			ceo = st.nextToken();
		}
		//return new CompanyBuilder().boId(boId).name(name).address(address).email(email).phone(phone).ceo(ceo)
			//	.buildCompany();
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + this.name;

	}
}

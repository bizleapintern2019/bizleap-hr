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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Company extends Entity {

	private String companyName;
	private String address;
	private String phone;
	private String email;
	private String ceo;
	private List<Employee> employeeList;

	Company() {
		super();
	}
	
	public Company(String workForBoId) {
		super(workForBoId);
	}
	
	public Company(String boId,String companyName, String address, String phone, String email, String ceo) {
		super.setBoId(boId);
		this.companyName = companyName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.ceo = ceo;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public void addEmployee(Employee employee) {
		if(employeeList == null){
			employeeList = new ArrayList<Employee>();
		}
		employeeList.add(employee);
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	
	public boolean boIdIsEqual(String boId) {
		return super.getBoId().equals(boId);
	}
	
	public static Company parseCompany(String line) {
		String boId, companyName, address, phone, email, ceo;
		StringTokenizer st = new StringTokenizer(line, ",");
		boId = st.nextToken();
		companyName = st.nextToken();
		address = st.nextToken();
		phone = st.nextToken();
		email = st.nextToken();
		ceo = st.nextToken();
		
		return new CompanyBuilder()
				.setAddress(address)
				.setBoId(boId)
				.setCeo(ceo)
				.setCompanyName(companyName)
				.setEmail(email)
				.setPhone(phone)
				.build();
	}

	@Override
	public String toString() {
		return super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Employee List "+ getEmployeeList());
	}
}
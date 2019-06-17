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

	private String name;
	private String address;
	private String ceo;
	private String phone;
	private String email;
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	public Company(String boid) {
		super(boid);
	}

	public Company(String boid, String name, String address, String phone, String email, String ceo) {
		super(boid);
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.ceo = ceo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Employee employee) {
		this.employeeList.add(employee);
	}
	
	public boolean checkCompany(String boid) {
		return getBoId().equals(boid);
	}

	public static Company parseCompany(String line) {
		String boid, companyName, address, phone, email, ceo;
		StringTokenizer st = new StringTokenizer(line, ",");
		boid = st.nextToken();
		companyName = st.nextToken();
		address = st.nextToken();
		phone = st.nextToken();
		email = st.nextToken();
		ceo = st.nextToken();
		return new CompanyBuilder()
				.boid(boid)
				.name(companyName)
				.address(address)
				.phone(phone)
				.email(email)
				.ceo(ceo)
				.buildCompany();
	}

	@Override
	public String toString() {
		return super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Employee list ",getEmployeeList());
	}
}

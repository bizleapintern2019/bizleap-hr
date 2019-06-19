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

	//	private String id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String ceo;
	private List<Employee> employeeList ;

	public Company() {
	}
	
	public Company(String boId){
		super(boId);
	}
	
	public Company(String boId,String name, String address, String phone, String email,String ceo){
		//	this.id = id;
		super.setBoId(boId);
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

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public void addEmployee(Employee employee){
		if(employeeList==null){
			employeeList = new ArrayList<Employee>();
		}
		employeeList.add(employee);
	}

	public static class Builder {
		private String boId;
		private String name;
		private String address;
		private String phone;
		private String email;
		private String ceo;

		public Builder() {}

		public Builder setBoId(String boId) {
			this.boId = boId;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setCeo(String ceo) {
			this.ceo = ceo;
			return this;
		}
		public Company build() {
			return new Company(boId,name,address,phone,email,ceo);
		}
	}

	public static Company parseCompany(String line) throws Exception{
		StringTokenizer tokenizer;
		String delimeter =",";
		String boId,cName,address,cEmail,ceo; 
		String cPhone;
		tokenizer= new StringTokenizer(line,delimeter);
		boId = tokenizer.nextToken();
		cName = tokenizer.nextToken();
		address = tokenizer.nextToken();
		cPhone = tokenizer.nextToken();
		cEmail = tokenizer.nextToken();
		ceo = tokenizer.nextToken();
		return new Company.Builder()
				.setBoId(boId)
				.setName(cName)
				.setAddress(address)
				.setPhone(cPhone)
				.setEmail(cEmail)
				.setCeo(ceo)
				.build();
	}

	@Override
	public String toString() {
	//	return String.format("id -> %s, company Name -> %s, employeList -> %s", getBoId(),name,employeeList);
		return "Company:"+super.toString()+
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
//				.append("boId",getBoId())
//				.append("name",getName())
//				.append("address",getAddress())
//				.append("phone",getPhone())
//				.append("email",getEmail())
//				.append("ceo",getCeo());
				.append("Company name:"+getName())
				.append("EmployeeList:"+getEmployeeList());		
	}



}

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

public class Employee extends Entity {

	private String firstname,lastname,title,email,phone;
	private int age,salary;
	private static List<Integer> lineNumbers;
	private Company workFor= new Company();
	
	
	public Employee(String boId) {
		super(boId);
	}

	public Employee(String boId,String firstname, String lastname, String title, String email, String phone, int age, int salary) {
		super.setBoId(boId);
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.salary = salary;
	}
	
	public Company getWorkFor() {
		return workFor;
	}


	public void setWorkFor(Company workFor) {
		this.workFor = workFor;
	}
	
	public String getWorkForBoId() {
		return this.workFor.getBoId();
	}

	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public static List<Integer> getLineNumberList() {
		return lineNumbers;
	}
	
	public boolean workForBoIdIsEqual(String companyBoId) {
		return getWorkForBoId().equals(companyBoId);
	}
	
	public static Employee parseEmployee(String line, int lineNumber) {
		
		if(lineNumbers == null)
			lineNumbers = new ArrayList<Integer>();
		else
			lineNumbers.add(lineNumber);
		
		String boId, firstName,lastName, title,email,phone,workForBoId;
		int age, salary;
		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		boId = tokenizer.nextToken();
		firstName = tokenizer.nextToken();
		lastName = tokenizer.nextToken();
		age = Integer.parseInt(tokenizer.nextToken());
		title = tokenizer.nextToken();
		salary = Integer.parseInt(tokenizer.nextToken());
		email = tokenizer.nextToken();
		phone = tokenizer.nextToken();
		workForBoId = tokenizer.nextToken();
		
		Employee employee= new EmployeeBuilder()
									 .setBoId(boId)
									 .setFirstName(firstName)
									 .setLastName(lastName)
									 .setAge(age)
									 .setPhone(phone)
									 .setEmail(email)
									 .setSalary(salary)
									 .setTitle(title)
									 .build();
		employee.setWorkFor(new Company(workForBoId));
		return employee;
	}
	
	public boolean checkEmployee(String boid) {
		return getWorkFor().getBoId().equals(boid);
	}

	@Override
	public String toString() {
		return "Employee: "+super.toString()+
				new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("boId :", getBoId())
				.append("FirstName: "+ getFirstname())
				.append("LastName: "+getLastname())
				.append("Work For: "+getWorkFor().getBoId());
	}
}
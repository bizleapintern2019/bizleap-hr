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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Employee extends Entity{
	private String firstName,lastName,title,email,phone;
	private int age,salary,lineNumber;
	private Company workFor= new Company();
	
	
	public Employee() {
		
	}

	public Employee(String boId) {
		super(boId);
	}

	public Employee(String boId,String firstName, String lastName, String title, String email, String phone, int age, int salary) {
		super.setBoId(boId);
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastname) {
		this.lastName = lastname;
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
	
	public boolean workForBoIdIsEqual(String companyBoId){
		return getWorkForBoId().equals(companyBoId);
	}

	public static class Builder {
		private String boId,firstName,lastName,title,phone,email;
		private int age, salary;

		public Builder(){}
		
		public Builder setBoId(String boId) {
			this.boId = boId;
			return this;
		}
		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
	
		public Builder setAge(int age) {
			this.age = age;
			return this;
		}
		
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
	
		public Builder setSalary(int salary) {
			this.salary = salary;
			return this;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Employee build() {
			return new Employee(boId,firstName,lastName,title,email,phone,age,salary);
		}
	}

	public static Employee parseEmployee(String dataLine) {
		
		String boId, firstName,lastName, title,email,phone,workForBoId;
		int age, salary;
		StringTokenizer tokenizer = new StringTokenizer(dataLine, ",");
		boId = tokenizer.nextToken();
		firstName = tokenizer.nextToken();
		lastName = tokenizer.nextToken();
		age = Integer.parseInt(tokenizer.nextToken());
		title = tokenizer.nextToken();
		salary = Integer.parseInt(tokenizer.nextToken());
		email = tokenizer.nextToken();
		phone = tokenizer.nextToken();
		workForBoId = tokenizer.nextToken();
		
		Employee employee= new Employee.Builder().setAge(age)
									 .setBoId(boId)
									 .setEmail(email)
									 .setFirstName(firstName)
									 .setLastName(lastName)
									 .setPhone(phone)
									 .setSalary(salary)
									 .setTitle(title)
									 .build();
		employee.setWorkFor(new Company(workForBoId));
		return employee;
	}
	
	@Override
	public String toString() {
		return  "Employee :"+super.toString()+
				new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("FirstName :"+ getFirstName())
				.append("LastName :"+getLastName())
				.append("work for :"+getWorkFor().getBoId());
	}
}

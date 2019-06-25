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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table( name  = "employee")
public class Employee extends AbstractEntity {
	
//	private String id;
	private String firstName;
	private String lastName;
	private int age;
	private String title;
	private int salary;
	private String email;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="companyId")
	private Company workFor;
	
	public Employee() {}
	
	public Employee(String boId) {
		super(boId);
	}

	public Employee(String boId,String firstName, String lastName, int age, String title, int salary, String email, String phone) {
		super.setBoId(boId);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.title = title;
		this.salary = salary;
		this.email = email;
		this.phone = phone;
	}
	
	public Company getWorkFor() {
		return workFor;
	}

	public void setWorkFor(Company workFor) {
		this.workFor = workFor;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public static class Builder {
		private String boId;
		private String firstName;
		private String lastName;
		private int age;
		private String title;
		private int salary;
		private String email;
		private String phone;
		

		public Builder() {}

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

		public Builder setSalary(int salary) {
			this.salary = salary;
			return this;
		}

		public Builder setEmail(String eEmail) {
			this.email = eEmail;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setBoId(String boId) {
			this.boId = boId;
			return this;
		}
	
		public Employee build() {
			return new Employee(boId,firstName,lastName,age,title,salary,email,phone);
		}
	}

	public static Employee parseEmployee(String line) throws Exception {
		
		StringTokenizer tokenizer;
		String delimeter =",";
		String firstName, lastName, title, empEmail, phone,boId,companyBoId; 
		int age, salary;
		tokenizer = new StringTokenizer(line,delimeter);
		boId = tokenizer.nextToken();
		firstName = tokenizer.nextToken();
		lastName = tokenizer.nextToken();
		age = Integer.parseInt(tokenizer.nextToken());
		title = tokenizer.nextToken();
		salary = Integer.parseInt(tokenizer.nextToken());
		empEmail = tokenizer.nextToken();
		phone = tokenizer.nextToken();
		 companyBoId = tokenizer.nextToken();
		 
		Employee employee = new Employee.Builder()
				.setFirstName(firstName)
				.setLastName(lastName)
				.setAge(age)
				.setTitle(title)
				.setSalary(salary)
				.setEmail(empEmail)
				.setPhone(phone)
				.setBoId(boId)
				.build();
		employee.setWorkFor(new Company(companyBoId));
		return employee;
	}
	
	public void displayInfo() {
	   String.format("COMPANY ID  -> %s, First Name -> %s, Last Name -> %s", getBoId(),firstName,lastName);
	}
	
	@Override
	public String toString() {
		return "Employee:"+super.toString()+
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("boId",getBoId())
				.append("firstName",getFirstName())
				.append("lastName",getLastName())
				.append("age",getAge())
//				.append("title",getTitle())
//				.append("salary",getSalary())
//				.append("email",getEmail())
//				.append("phone",getPhone())
				.append("companyBoId",getWorkFor().getBoId());
	}
}

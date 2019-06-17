package com.bizleap.commons.domain.entity;

public class EmployeeBuilder {
	private String boId,firstName,lastName,title,phone,email;
	private int age, salary;

	public EmployeeBuilder(){}
	
	public EmployeeBuilder setBoId(String boId) {
		this.boId = boId;
		return this;
	}
	public EmployeeBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public EmployeeBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public EmployeeBuilder setAge(int age) {
		this.age = age;
		return this;
	}
	
	public EmployeeBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public EmployeeBuilder setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public EmployeeBuilder setSalary(int salary) {
		this.salary = salary;
		return this;
	}
	
	public EmployeeBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public Employee build() {
		return new Employee(boId,firstName,lastName,title,email,phone,age,salary);
	}
}
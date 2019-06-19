package com.bizleap.commons.domain.entity;

public class EmployeeBuilder {
	private String boid;
	private String employeeId;
	private String firstName;
	private String lastName;
	private int age;
	private String title;
	private int salary;
	private String email;
	private String phone;

	public Employee buildEmployee() {
		return new Employee(employeeId, firstName, lastName, age ,title, salary, email, phone, boid);
	}

	public EmployeeBuilder employeeId(String employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	public EmployeeBuilder boid(String boid) {
		this.boid = boid;
		return this;
	}

	public EmployeeBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public EmployeeBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public EmployeeBuilder age(int age) {
		this.age = age;
		return this;
	}

	public EmployeeBuilder title(String title) {
		this.title = title;
		return this;
	}

	public EmployeeBuilder salary(int salary) {
		this.salary = salary;
		return this;
	}

	public EmployeeBuilder email(String email) {
		this.email = email;
		return this;
	}

	public EmployeeBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}
}



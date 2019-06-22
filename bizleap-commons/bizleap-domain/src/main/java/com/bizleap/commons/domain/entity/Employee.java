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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name="employee")
public class Employee extends AbstractEntity {

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
	
	int	count=0;
	private static List<Integer> lineNumbers;

	public Employee(String boid){
		super(boid);
	}

	public Employee(String employeeId, String firstName, String lastName, int age, String title, int salary, String email, String phone, String boid) {
		super(boid);
		this.firstName = firstName;
		this.lastName = lastName;	
		this.age = age;
		this.title = title;
		this.salary = salary;
		this.email = email;
		this.phone = phone;
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

	public Company getWorkFor() {
		return workFor;
	}

	public void setWorkFor(Company workFor) {
		this.workFor = workFor;
	}
	
	public static List<Integer> getLineNumberList(){
		return lineNumbers;
	}
	
	public static Employee parseEmployee(String line, int lineNumber) {
		if(lineNumbers == null)
			lineNumbers = new ArrayList<Integer>();
		else
			lineNumbers.add(lineNumber);
		
		String boid, firstName, lastName, title, email, phone, companyBoid;
		int age, salary;

		StringTokenizer st = new StringTokenizer(line, ",");

		boid = st.nextToken();
		firstName = st.nextToken();
		lastName = st.nextToken();
		age = Integer.parseInt(st.nextToken());
		title = st.nextToken();
		salary = Integer.parseInt(st.nextToken());
		email = st.nextToken();
		phone = st.nextToken();
		companyBoid = st.nextToken();

		Employee employee = new EmployeeBuilder()
				.boid(boid)
				.firstName(firstName)
				.lastName(lastName)
				.age(age)
				.title(title)
				.salary(salary)
				.email(email)
				.phone(phone)
				.buildEmployee();
		employee.setWorkFor(new Company(companyBoid));
		return employee;
	}
	
	public boolean checkEmployee(String boid) {
		return getWorkFor().getBoId().equals(boid);
	}

	@Override
	public String toString() {
		return 	super.toString()+
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("firstname",getFirstName())
				.append("lastname",getLastName())
				.append("work for",getWorkFor().getBoId());
	}
}

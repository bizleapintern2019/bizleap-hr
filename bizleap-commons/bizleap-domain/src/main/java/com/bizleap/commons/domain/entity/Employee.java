package com.bizleap.commons.domain.entity;

import javax.persistence.Entity;
 
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity{
	private String firstName,lastName,title,email,phone;
	private int age,salary;
	
	public Employee(){
		super();
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

	public static Employee parseEmployee(String dataLine) {
		Employee employee = new Employee();
        String[] tokens = dataLine.split(";");
        employee.setBoId(tokens[0]);
        employee.setFirstName(tokens[1]);
        employee.setLastName(tokens[2]);
        employee.setAge(Integer.parseInt(tokens[3]));
        employee.setTitle(tokens[4]);
        employee.setSalary(Integer.parseInt(tokens[5]));
        employee.setEmail(tokens[6]);
        employee.setPhone(tokens[7]);
        return employee;
	}
	
	@Override
	public String toString() {
		return  "Employee :"+super.toString()+
				new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("FirstName :"+ getFirstName())
				.append("LastName :"+getLastName());
	}
}
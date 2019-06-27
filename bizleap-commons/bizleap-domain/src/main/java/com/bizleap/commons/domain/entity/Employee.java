package com.bizleap.commons.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@author: Saw Sandi Tin
@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity {

	private String title,firstName, lastName, entranceDate, dateOfBirth, gender, email, phone;
	
	@OneToOne
	@JoinColumn(name="positionId")
	private Position position;
	
	@OneToOne
	@JoinColumn(name="addressId")
	private Address address;

	public Employee() {
		super();
	}

	public Employee(String boId) {
		super(boId);
	}

	public Employee(String boId,String title, String firstName, String lastName, String entranceDate, String dateOfBirth,
			String gender, String email, String phone, Position position, Address address) {
		super.setBoId(boId);
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.entranceDate = entranceDate;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.position = position;
		this.address = address;
	}

	public String getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(String entranceDate) {
		this.entranceDate = entranceDate;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		employee.setTitle(tokens[1]);
		employee.setFirstName(tokens[2]);
		employee.setLastName(tokens[3]);
		employee.setPosition(new Position(tokens[4]));
		employee.setEntranceDate(tokens[5]);
		employee.setDateOfBirth(tokens[6]);
		employee.setGender(tokens[7]);
		employee.setEmail(tokens[8]);
		employee.setPhone(tokens[9]);
		employee.setAddress(new Address(tokens[10]));
		return employee;
	}

	@Override
	public String toString() {
		return "Employee :" + super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("FirstName :" + getFirstName()).append("LastName :" + getLastName());
	}
}
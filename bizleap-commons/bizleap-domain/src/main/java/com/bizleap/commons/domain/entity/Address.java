package com.bizleap.commons.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@author: Thuya Oo, Shine Wanna
@Entity
@Table(name = "address")
public class Address extends AbstractEntity {
	private String permanentAddress;
	private String contactAddress;
	private String city;
	private String state;
	private String country;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Employee employee;
	
	public Address() {
		super();
	}

	public Address(String boId) {
		super(boId);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Address(String boId,String permanentAddress,String contactAddress,String city,String state,String country) {
		super.setBoId(boId);
		this.permanentAddress = permanentAddress;
		this.contactAddress = contactAddress;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public static Address parseAddress(String dataLine) {
		Address address = new Address();
		String[] tokens = dataLine.split(";");
		address.setBoId(tokens[0]);
		address.setPermanentAddress(tokens[1]);
		address.setContactAddress(tokens[2]);
		address.setCity(tokens[3]);
		address.setState(tokens[4]);
		address.setCountry(tokens[5]);
		return address;
	}

	@Override
	public String toString() {
		return  "Address :"+super.toString() +
				new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Permanent: " + getPermanentAddress())
				.append("Contact: " + getContactAddress())
				.append("City: " + getCity())
				.append("State: " + getState())
				.append("Country: " + getCountry());
	}
}
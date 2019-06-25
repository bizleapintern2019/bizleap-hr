package com.bizleap.commons.domain.entity;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "address")
public class Address extends AbstractEntity{
	
	private String permanentAddress;
	private String contactAddress;
	private String city;
	private String state;
	private String country;
	
	public Address() {
		super();
	}
	
	public Address(String boId) {
		super(boId);
	}
	
	public Address(String boId,String permanentAddress,String contactAddress,String city,String state,String country){
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
	
	public static Address parse(String dataLine) {
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
		return  "Address :"+super.toString()+
				new ToStringBuilder(this,ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Permanent Address :"+ getPermanentAddress())
				.append("Contact Address :"+getContactAddress())
				.append("City :"+getCity())
		 		.append("State :"+getState())
		 		.append("Country :"+getCountry());
	}
=======
public class Address {

>>>>>>> 648de4acf9293affdde9b06313eafb329ab15316
}

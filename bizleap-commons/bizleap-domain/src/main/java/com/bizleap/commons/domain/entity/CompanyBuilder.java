package com.bizleap.commons.domain.entity;

public class CompanyBuilder {

	private String boid;
	private String email;
	private String phone;
	private String name;
	private String address;
	private String ceo;

	public Company buildCompany() {
		return new Company(boid, name, address, phone, email, ceo);
	}
	
	public CompanyBuilder boid(String boid) {
		this.boid = boid;
		return this;
	}

	public CompanyBuilder email(String email) {
		this.email = email;
		return this;
	}

	public CompanyBuilder phone(String phone) {
		this.phone = phone;
		return this;
	}

	public CompanyBuilder name(String name) {
		this.name = name;
		return this;
	}

	public CompanyBuilder address(String address) {
		this.address = address;
		return this;
	}

	public CompanyBuilder ceo(String ceo) {
		this.ceo = ceo;
		return this;
	}
}

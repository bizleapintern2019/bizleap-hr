package com.bizleap.commons.domain.entity;


public class CompanyBuilder {
	private String boId,companyName, address, phone, email, ceo;

	public CompanyBuilder(){}
	
	public CompanyBuilder setBoId(String boId) {
		this.boId = boId;
		return this;
	}
	
	public CompanyBuilder setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
	
	public CompanyBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	
	public CompanyBuilder setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public CompanyBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public CompanyBuilder setCeo(String ceo) {
		this.ceo = ceo;
		return this;
	}
	
	public Company build() {
		return new Company(boId,companyName,address,phone,email,ceo);
	}
}
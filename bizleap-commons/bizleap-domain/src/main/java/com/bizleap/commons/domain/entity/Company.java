//package com.bizleap.commons.domain.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//
//@Entity
//@Table(name = "company")
//public class Company extends AbstractEntity {
//	private String companyName;
//	private String address;
//	private String phone;
//	private String email;
//	private String ceo;
//
//	@OneToMany(mappedBy = "workFor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Employee> employeeList;
//
//	public Company() {
//		super();
//	}
//
//	public Company(String workForBoId) {
//		super(workForBoId);
//	}
//
//	public Company(String boId, String companyName, String address, String phone, String email, String ceo) {
//		super.setBoId(boId);
//		this.companyName = companyName;
//		this.address = address;
//		this.phone = phone;
//		this.email = email;
//		this.ceo = ceo;
//	}
//
//	public List<Employee> getEmployeeList() {
//		return employeeList;
//	}
//
//	public void setEmployeeList(List<Employee> employeeList) {
//		this.employeeList = employeeList;
//	}
//
//	public void addEmployee(Employee employee) {
//		if (employeeList == null) {
//			employeeList = new ArrayList<Employee>();
//		}
//		employeeList.add(employee);
//	}
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getCeo() {
//		return ceo;
//	}
//
//	public void setCeo(String ceo) {
//		this.ceo = ceo;
//	}
//
//	public boolean sameBoId(String boId) {
//		return super.getBoId().equals(boId);
//	}
//
//	public static Company parseCompany(String dataLine) {
//		Company company = new Company();
//		String[] tokens = dataLine.split(";");
//		company.setBoId(tokens[0]);
//		company.setCompanyName(tokens[1]);
//		company.setCeo(tokens[2]);
//		return company;
//	}
//
//	@Override
//	public String toString() {
//		return "Company: " + super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
//				.append("Company Name: " + getCompanyName()).append("Employee List: " + getEmployeeList());
//	}
//}
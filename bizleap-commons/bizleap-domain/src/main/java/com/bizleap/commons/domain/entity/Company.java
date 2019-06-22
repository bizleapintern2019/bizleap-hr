package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name= "company")

public class Company extends AbstractEntity {
	private String name;
	private String address;
	private String ceo;
	private String phone;
	private String email;
	
	@OneToMany(mappedBy="workFor",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	public Company(String boid) {
		super(boid);
	}

	public Company(String boid, String name, String address, String phone, String email, String ceo) {
		super(boid);
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.ceo = ceo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Employee employee) {
		this.employeeList.add(employee);
	}
	
	public boolean checkCompany(String boid) {
		return getBoId().equals(boid);
	}

	public static Company parseCompany(String line) {
		String boid, companyName, address, phone, email, ceo;
		StringTokenizer st = new StringTokenizer(line, ",");
		boid = st.nextToken();
		companyName = st.nextToken();
		address = st.nextToken();
		phone = st.nextToken();
		email = st.nextToken();
		ceo = st.nextToken();
		return new CompanyBuilder()
				.boid(boid)
				.name(companyName)
				.address(address)
				.phone(phone)
				.email(email)
				.ceo(ceo)
				.buildCompany();
	}

	@Override
	public String toString() {
		return super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Employee List ",getEmployeeList());
	}
}



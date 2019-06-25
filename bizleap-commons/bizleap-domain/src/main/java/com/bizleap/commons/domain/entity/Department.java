package com.bizleap.commons.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name ="department")
public class Department extends AbstractEntity {
	private String departmentName;
	private String parentDepartment;
	private String locationId;

	public Department() {
		super();
	}

	public Department(String boId, String departmentName, String parentDepartment, String locationId) {
		super.setBoId(boId);
		this.departmentName = departmentName;
		this.parentDepartment = parentDepartment;
		this.locationId = locationId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(String parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Department parseDepartment(String dataLine) {
		Department department = new Department();
		String[] tokens = dataLine.split(";");
		department.setBoId(tokens[0]);
		department.setDepartmentName(tokens[1]);
		department.setParentDepartment(tokens[2]);
		department.setLocationId(tokens[3]);
		return department;
	}

	@Override
	public String toString() {
		return "Department :" + super.toString()
				+ new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
						.append("DepartmentName :" + getDepartmentName())
						.append("ParentDepartment :" + getParentDepartment()).append("Location ID :" + getLocationId());
	}
}

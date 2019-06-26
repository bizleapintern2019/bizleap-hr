package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name ="department")
public class Department extends AbstractEntity {
	
	private String departmentName;
	private Department parentDepartment;
	private String locationId;
	private List<Job> jobList;

	public Department() {
		super();
	}

	public Department(String boId, String departmentName, Department parentDepartment, String locationId) {
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

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public void addJob(Job job) {
		if(jobList == null){
			jobList = new ArrayList<Job>();
		}
		jobList.add(job);
	}
	
	public static Department parseDepartment(String dataLine) {
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
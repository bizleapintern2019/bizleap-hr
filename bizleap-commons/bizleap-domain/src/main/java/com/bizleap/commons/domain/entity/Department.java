package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@author: Thuya Oo, Shine Wanna
@Entity
@Table(name ="department")
public class Department extends AbstractEntity {
	
	private String name;
	
	@OneToOne
	private Department parentDepartment;
	
	@ManyToOne
	@JoinColumn(name="locationId")
	private Location location;
	
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Job> jobList;

	public Department() {
		super();
	}
	public Department(String boId){
		super(boId);	
	}

	public Department(String boId, String name, Location location, Department parentDepartment) {
		super.setBoId(boId);
		this.name = name;
		this.location = location;
		this.parentDepartment = parentDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public void addJob(Job job) {
		if(getJobList() == null){
			jobList = new ArrayList<Job>();
		}
		jobList.add(job);
	}
	
	public static Department parseDepartment(String dataLine) {
		Department department = new Department();
		String[] tokens = dataLine.split(";");
		department.setBoId(tokens[0]);
		department.setName(tokens[1]);
		department.setLocation(new Location(tokens[2]));
		String parentDepartment = tokens[3];
		if(parentDepartment != "null")
			department.setParentDepartment(new Department(parentDepartment));
		else 
			department.setParentDepartment(null);
		return department;
	}

	@Override
	public String toString() {
		return "Department :" + super.toString()
				+ new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
						.append("DepartmentName :" + getName())
						.append("ParentDepartment :" + getParentDepartment()).append("Location ID :" + getLocation().getBoId());
	}
}
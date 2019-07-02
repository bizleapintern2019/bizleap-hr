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
	private Location location;
	
	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Job> jobList;

	public Department() {
		super();
	}
	
	public Department(String boId) {
		super(boId);	
	}

	public Department(String boId, String name, Department parentDepartment) {
		super.setBoId(boId);
		this.name = name;
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
		String parentDepartment = tokens[2];
		if(parentDepartment != " ")
			department.setParentDepartment(new Department(parentDepartment));
		else 
			department.setParentDepartment(null);
		return department;
	}
	
	private String toBoIdList(List<Job> jobList) {
		if(jobList == null) {
			return "";
		}
	
		String boIds = " ";
		for(Job job : jobList) {
			boIds += job.getBoId() + " ";
		}
		return boIds;
	}

	@Override
	public String toString() {
		return "Department :" + super.toString() +
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Name: " + getName())
				.append("At Location: "+ getLocation())
				.append("Parent: " +
				(getParentDepartment()!= null ? getParentDepartment().getBoId() : "")+
				" ;ParentDepartment Name: " +(getParentDepartment()!= null ? getParentDepartment().getName() : ""))
				.append("Job List: " + toBoIdList(getJobList()));
	} 
}
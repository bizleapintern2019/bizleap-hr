package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "job")
public class Job extends AbstractEntity {
	private String jobTitle;
	private int salary;
	private String departmentId;
	private List<Position> positionList;

	public Job() {
		super();
	}

	public Job(String boId, String jobTitle, int salary, String departmentId) {
		super.setBoId(boId);
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.departmentId = departmentId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	public void addPosition(Position position) {
		if(positionList == null) {
			positionList = new ArrayList<Position>();
		}
		positionList.add(position);
	}

	public static Job parseJob(String dataLine) {
		Job job = new Job();
		String[] tokens = dataLine.split(";");
		job.setBoId(tokens[0]);
		job.setJobTitle(tokens[1]);
		job.setSalary(Integer.parseInt(tokens[2]));
		job.setDepartmentId(tokens[3]);
		return job;
	}

	@Override
	public String toString() {
		return "Job :" + super.toString()
				+ new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE).append("Job Title" + getJobTitle())
						.append("Salary" + getSalary()).append("Department Id " + getDepartmentId());
	}
}

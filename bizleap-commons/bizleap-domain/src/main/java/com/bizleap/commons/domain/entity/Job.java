package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@author: Saw Sandi Tin
@Entity
@Table(name = "job")
public class Job extends AbstractEntity {
	private String jobTitle;
	private int salary;
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;
	
	@OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Position> positionList;

	public Job() {
		super();
	}
	
	public Job(String boId) {
		super(boId);
	}

	public Job(String boId, String jobTitle, int salary, Department department) {
		super.setBoId(boId);
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.department = department;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}
	

	public void addPosition(Position position) {
		if (getPositionList() == null) {
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
		job.setDepartment(new Department(tokens[3]));
		return job;
	}

	@Override
	public String toString() {
		return "Job: " + super.toString() + 
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("Title: " + getJobTitle())
				.append("Salary: " + getSalary()).append("Department: " + getDepartment().getBoId());
	}
}
package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "position")
public class Position extends AbstractEntity {
	
	private Job job;
	private Position reportTo;
	private List<Employee> employeeList;

	public Position() {
		super();
	}
	
	public Position(String boId) {
		super(boId);
	}

	public Position(String boId, Job job, Position reportTo) {
		super.setBoId(boId);
		this.job = job;
		this.reportTo = reportTo;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Position getReportTo() {
		return reportTo;
	}

	public void setReportTo(Position reportTo) {
		this.reportTo = reportTo;
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public void addEmployee(Employee employee) {
		if (getEmployeeList() == null) {
			employeeList = new ArrayList<Employee>();
		}
		employeeList.add(employee);
	}

	public static Position parsePosition(String dataLine) {
		Position position = new Position();
		String[] tokens = dataLine.split(";");
		position.setBoId(tokens[0]);
		position.setJob(new Job(tokens[1]));
		position.setReportTo(new Position(tokens[2]));
		return position;
	}

	@Override
	public String toString() {
		return "Position " + super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("JobId" + getJob().getBoId()).append("ReportTo" + getReportTo());
	}
}
package com.bizleap.commons.domain.entity;

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

//@author: Su Pyae Naing
@Entity
@Table(name = "position")
public class Position extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name="jobId")
	private Job job;
	
	@OneToMany
	private List<Position> reportToList;
	
	@ManyToOne
	private List<Position> reportByList;

	@OneToOne(mappedBy = "position", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Employee employee;
	

	public Position() {
		super();
	}
	
	public Position(String boId) {
		super(boId);
	}

	public Position(String boId, Job job, List<Position> reportTo) {
		super.setBoId(boId);
		this.job = job;
		this.reportToList = reportTo;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Position> getReportToList() {
		return reportToList;
	}

	public void setReportToList(List<Position> reportTo) {
		this.reportToList = reportTo;
	}
	
	public List<Position> getReportByList() {
		return reportByList;
	}

	public void setReportByList(List<Position> reportBy) {
		this.reportByList = reportBy;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public static Position parsePosition(String dataLine) {
		Position position = new Position();
		String[] tokens = dataLine.split(";");
		position.setBoId(tokens[0]);
		position.setJob(new Job(tokens[1]));
		String[] reportToBoIds = tokens[2].split(",");
		for(int i=0; i<reportToBoIds.length; i++)
			position.getReportToList().add(new Position(reportToBoIds[i]));
		position.setReportToList(position.getReportToList());
		return position;
	}

	@Override
	public String toString() {
		return "Position " + super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("JobId" + getJob().getBoId())
				.append("ReportTo" + getReportToList())
				.append("ReportBy" + getReportByList());
	}
}
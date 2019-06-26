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
	private String jobId;
	private String reportTo;
	private String reportBy;
	private List<Employee> employeeList;

	public Position() {
		super();
	}

	public Position(String boId, String jobId, String reportTo, String reportBy) {
		super.setBoId(boId);
		this.jobId = jobId;
		this.reportTo = reportTo;
		this.reportBy = reportBy;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getReportTo() {
		return reportTo;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	public String getReportBy() {
		return reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}

	public void addEmployee(Employee employee) {
		if (employeeList == null) {
			employeeList = new ArrayList<Employee>();
		}
		employeeList.add(employee);
	}

	public static Position parsePosition(String dataLine) {
		Position position = new Position();
		String[] tokens = dataLine.split(";");
		position.setBoId(tokens[0]);
		position.setJobId(tokens[1]);
		position.setReportTo(tokens[2]);
		position.setReportBy(tokens[3]);
		return position;
	}

	@Override
	public String toString() {
		return "Position " + super.toString() + new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				.append("JobId" + getJobId()).append("ReportTo" + getReportTo()).append("Report By" + getReportBy());
	}
}
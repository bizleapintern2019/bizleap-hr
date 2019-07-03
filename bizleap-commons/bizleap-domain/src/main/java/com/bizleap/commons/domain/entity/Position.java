package com.bizleap.commons.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@author: Su Pyae Naing
@Entity
@Table(name = "position")
public class Position extends AbstractEntity {
	
	/*@ManyToOne
	@JoinColumn(name="jobId")*/
	@Transient
	private Job job;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "position_reportToList",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "reportToList_id")
    )
	private List<Position> reportToList;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "position_reportByList",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "reportByList_id")
    )
	private List<Position> reportByList;

//	@OneToOne(mappedBy = "position", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

	public Position(String boId, Job job) {
		super.setBoId(boId);
		this.job = job;
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

	public void setReportToList(List<Position> reportToList) {
		this.reportToList = reportToList;
	}
	
	public List<Position> getReportByList() {
		return reportByList;
	}

	public void setReportByList(List<Position> reportByList) {
		this.reportByList = reportByList;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void addReportBy(Position reportBy) {
		
		if(getReportByList() == null){
			setReportByList(new ArrayList<Position>());
		}
		getReportByList().add(reportBy);
	}
	
	public static Position parsePosition(String dataLine) {
		
		Position position = new Position();
		String[] tokens = dataLine.split(";");
		position.setBoId(tokens[0]);
		position.setJob(new Job(tokens[1]));
		String[] reportToBoIds = tokens[2].split(",");
		
		if(position.getReportToList()==null)
			position.setReportToList(new ArrayList<Position>());
	
		for(int i=0; i<reportToBoIds.length; i++)
			position.getReportToList().add(new Position(reportToBoIds[i]));
		return position;
	}
	
	private String toBoIdList(List<Position> positionList) {
		if(positionList == null) {
			return "";
		}
	
		String boIds = "";
		for(Position position : positionList) {
			boIds += position.getBoId() + " ";
		}
		return boIds;
	}
	
	@Override
	public String toString() {
		return "Position " + super.toString() + 
				new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
				//.append("JobId: " + getJob().getJobTitle())
				.append("ReportTo: " + toBoIdList(getReportToList()))
				.append("ReportBy: "+toBoIdList(getReportByList()));
	}
}
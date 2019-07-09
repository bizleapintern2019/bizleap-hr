package com.bizleap.commons.domain.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@author: Su Pyae Naing
@Entity
@Table(name = "position")
public class Position extends AbstractEntity {
	
	private static Logger logger = Logger.getLogger(Position.class);
	/*@ManyToOne
	@JoinColumn(name="jobId")*/
	@JsonIgnore
	@Transient
	private Job job;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "position_reportToList",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "reportToList_id")
    )
	private List<Position> reportToList;
	
	@JsonIgnore
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
	
	public void addReportTo(Position reportTo) {
		
		if(getReportToList() == null){
			setReportToList(new ArrayList<Position>());
		}
		getReportToList().add(reportTo);
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
		logger.info("Token0: "+tokens[0]);
		logger.info("Token1: "+tokens[1]);
		logger.info("Token2: "+tokens[2]);
		
		if(tokens[2] == null || tokens[2].trim().length() <= 0) {
			position.setReportToList(null);
			logger.info("Position1111: "+position);
			return position;
		}
	
		for(int i=0; i<reportToBoIds.length; i++) {
			if(StringUtils.isNotEmpty(reportToBoIds[i])) {
				position.addReportTo(new Position(reportToBoIds[i]));
			}	
		}
		for(Position position1: position.getReportToList()) {
			if(StringUtils.isEmpty(position1.getBoId())) {
				logger.info("Position2222: "+position1);
				System.exit(0);
			}
		}
//		logger.info("Position3333: "+position);
//		System.exit(0);
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
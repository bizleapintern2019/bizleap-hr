package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

// @Author: San Thinzar Linn, Yamone Zin
@Service
public class AssociationMapperImpl implements AssociationMapper {
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	private void addLocationToDepartment(Department department) {
		for(Location location : dataManager.getLocationList()) {
			if(department.getLocation().sameBoId(location)) {
				department.setLocation(location);
			}
		}
		errorHandler.handleLinkageError("Location in department cannot be linked.", department);
	}
	
	private void setUpLocationAssociations() {
		for(Location location : dataManager.getLocationList()) {
			addDepartmentToLocation(location);
		}
	}
	
	private void addDepartmentToLocation(Location location) {
		for(Department department : dataManager.getDepartmentList()) {
			if(location.sameBoId(department.getLocation())) 
				location.addDepartment(department);
		}
		errorHandler.handleLinkageError("Department in location cannot be linked.", location);
	}
	
	private void setUpDepartmentAssociations() {
		for(Department department : dataManager.getDepartmentList()) {
			addLocationToDepartment(department);
			addJobToDepartment(department);
		}
	}
	
	private void addJobToDepartment(Department department) {
		for(Job job : dataManager.getJobList()) {
			if(job.getDepartment().sameBoId(department))
				department.addJob(job);
		}
		errorHandler.handleLinkageError("Job in department cannot be linked.", department);
	}
	
	private void addPositionToJob(Job job) {
		for(Position position : dataManager.getPositionList()) {
			if(position.getJob().sameBoId(job)) 
				job.addPosition(position);
		}
		errorHandler.handleLinkageError("Position in job cannot be linked.", job);
	}
	
	private void addDepartmentToJob(Job job) {
		for(Department department : dataManager.getDepartmentList()) {
			if(department.sameBoId(job.getDepartment())) 
				job.setDepartment(department);	
		}
		errorHandler.handleLinkageError("Position in job cannot be linked.", job);
	}
	
	private void setUpJobAssociations() {
		for(Job job : dataManager.getJobList()) {
			addDepartmentToJob(job);
			addPositionToJob(job);
		}
	}
	
	private void addEmployeeToPosition(Position position) {
		for(Employee employee : dataManager.getEmployeeList()) {
			if(employee.getPosition().sameBoId(position)) 
				position.setEmployee(employee);
		}
		errorHandler.handleLinkageError("Employee in position cannot be linked.", position);
	}
	
	private void addReportToandReportBy(Position position) {
		List<Position> reportToList = new ArrayList<Position>();
		for(Position reportTo : position.getReportToList()) {
			for(Position reportBy : dataManager.getPositionList()) {
				if(reportTo.sameBoId(reportBy)) {
					position.getReportByList().add(reportBy);
					reportTo = position;
					reportToList.add(reportTo);
				}
			}
		}
		position.setReportToList(reportToList);
	}
	
	private void setUpPositionAssociations() {
		for(Position position : dataManager.getPositionList()) {
			addEmployeeToPosition(position);
			addReportToandReportBy(position);
		}
	}
	
	private void addAddressToEmployee(Employee employee) {
		for(Address address : dataManager.getAddressList()) {
			if(address.sameBoId(employee.getAddress())) 
				employee.setAddress(address);
		}
		errorHandler.handleLinkageError("Address in employee cannot be linked.", employee);
	}
	
	private void addPositionToEmployee(Employee employee) {
		for(Position position : dataManager.getPositionList()) {
			if(position.sameBoId(employee.getPosition())) 
				employee.setPosition(position);
		}
		errorHandler.handleLinkageError("Address in employee cannot be linked.", employee);
	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee : dataManager.getEmployeeList()) {
			addPositionToEmployee(employee);
			addAddressToEmployee(employee);
		}
	}
	
	public void setUpAssociations() {
		setUpLocationAssociations();
		setUpDepartmentAssociations();
		setUpJobAssociations();
		setUpPositionAssociations();
		setUpEmployeeAssociations();	
	}
}
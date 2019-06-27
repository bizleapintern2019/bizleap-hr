package com.bizleap.hr.loader.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorHandler;

// @Author: San Thinzar Linn, Yamone Zin
@Service
public class AssociationMapperImpl implements AssociationMapper {
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private DataLoader dataLoader;
	
	int lineNumber = 0;
	
	private void setUpLocationAssociations() {
		for(Location location : dataManager.getLocationList()) {
			addDepartmentToLocation(location);
		}
	}
	
	private void addDepartmentToLocation(Location location) {
		for(Department department : dataManager.getDepartmentList()) {
			if(department.getLocation().sameBoId(location)) 
				location.addDepartment(department);
		}
		errorHandler.handleLinkageError("Department in location cannot be linked.", location);
	}
	
	private void setUpDepartmentAssociations() {
		for(Department department : dataManager.getDepartmentList())
			addJobToDepartment(department);
	}
	
	private void addJobToDepartment(Department department) {
		for(Job job : dataManager.getJobList()) {
			if(job.getDepartment().sameBoId(department))
				department.addJob(job);
		}
		errorHandler.handleLinkageError("Job in department cannot be linked.", department);
	}
	
	private void setUpJobAssociations() {
		for(Job job : dataManager.getJobList()) {
			addPositionToJob(job);
		}
	}
	
	private void addPositionToJob(Job job) {
		for(Position position : dataManager.getPositionList()) {
			if(position.getJob().sameBoId(job)) 
				job.addPosition(position);
		}
		errorHandler.handleLinkageError("Position in job cannot be linked.", job);
	}
	
	private void setUpPositionAssociations() {
		for(Position position : dataManager.getPositionList()) 
			addEmployeeToPosition(position);
	}
	
	private void addEmployeeToPosition(Position position) {
		for(Employee employee : dataManager.getEmployeeList()) {
			if(employee.getPosition().sameBoId(position)) 
				position.setEmployee(employee);
		}
		errorHandler.handleLinkageError("Employee in position cannot be linked.", position);
	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee : dataManager.getEmployeeList()) 
			addAddressToEmployee(employee);
	}
	
	private void addAddressToEmployee(Employee employee) {
		for(Address address : dataManager.getAddressList()) {
			if(address.sameBoId(employee.getAddress())) 
				employee.setAddress(address);
		}
		errorHandler.handleLinkageError("Address in employee cannot be linked.", employee);
	}
	
	public void setUpAssociations() {
		setUpLocationAssociations();
		setUpDepartmentAssociations();
		setUpJobAssociations();
		setUpPositionAssociations();
		setUpEmployeeAssociations();	
	}
}
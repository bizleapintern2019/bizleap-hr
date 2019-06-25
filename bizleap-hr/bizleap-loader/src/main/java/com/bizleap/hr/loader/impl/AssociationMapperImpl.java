package com.bizleap.hr.loader.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;

@Service
public class AssociationMapperImpl implements AssociationMapper {
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	private ErrorCollector errorCollector;
	
	@Autowired
	private DataLoader dataLoader;
	
	int lineNumber = 0;
	
//	private void addEmployeesToCompany(Company company) {
//		for(Employee employee : dataManager.getEmployeeList()) {
//			if(employee.getWorkFor().isEqual(company.getBoId())) {
//				company.addEmployee(employee);
//			}
//		}
//	}
//
//	private void setUpCompanyAssociations() {
//		for(Company company : dataManager.getCompanyList()) {
//			addEmployeesToCompany(company);
//			addLocationToCompany(company);
//		}	
//	}
//	
//	private void addCompanyToEmployee(Employee employee) {
//		for(Company company : dataManager.getCompanyList()) {
//			if(company.sameBoId(employee.getWorkFor())) {
//				employee.setWorkFor(company);
//				return;
//			}
//		}
//		lineNumber=dataLoader.getIndex();
//		errorCollector.handleLinkageError(lineNumber,"Company in employee cannot be linked.", employee);
//		lineNumber++;
//	}
//	
//	private void addLocationToCompany(Company company) {
//		for(Location location : dataManager.getLocationList()) {
//			if(location.sameBoId(company.getBoId())) {
//				company.addLocation(location);
//			}
//		}
//	}
	
	private void setUpLocationAssociations() {
		for(Location location : dataManager.getLocationList()) {
			addDepartmentToLocation(location);
		}
	}
	
	private void addDepartmentToLocation(Location location) {
		for(Department department : dataManager.getDepartmentList()) {
			if(department.sameBoId(location)) {
				location.addDepartment(department);
			}
		}
		lineNumber=dataLoader.getIndex();
		errorCollector.handleLinkageError(lineNumber,"Department in location cannot be linked.", location);
		lineNumber++;
	}
	
	private void setUpDepartmentAssociations() {
		for(Department department : dataManager.getDepartmentList()) {
			addJobToDepartment(department);
		}
	}
	
	private void addJobToDepartment(Department department) {
		for(Job job : dataManager.getJobList()) {
			if(job.sameBoId(department)) {
				department.addJob(job);
			}
		}
		lineNumber=dataLoader.getIndex();
		errorCollector.handleLinkageError(lineNumber,"Job in department cannot be linked.", department);
		lineNumber++;
	}
	
	private void setUpJobAssociations() {
		for(Job job : dataManager.getJobList()) {
			addPositionToJob(job);
		}
	}
	
	private void addPositionToJob(Job job) {
		for(Position position : dataManager.getPositionList()) {
			if(position.sameBoId(job)) {
				job.addPosition(position);
			}
		}
		lineNumber=dataLoader.getIndex();
		errorCollector.handleLinkageError(lineNumber,"Position in job cannot be linked.", job);
		lineNumber++;
	}
	
	private void setUpPositionAssociations() {
		for(Position position : dataManager.getPositionList()) {
			addEmployeeToPosition(position);
		}
	}
	
	private void addEmployeeToPosition(Position position) {
		for(Employee employee : dataManager.getEmployeeList()) {
			if(employee.sameBoId(position)) {
				position.addEmployee(employee);
			}
		}
		lineNumber=dataLoader.getIndex();
		errorCollector.handleLinkageError(lineNumber,"Employee in position cannot be linked.", position);
		lineNumber++;
	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee : dataManager.getEmployeeList()) {
			addAddressToEmployee(employee);
		}
	}
	
	private void addAddressToEmployee(Employee employee) {
		for(Address address : dataManager.getAddressList()) {
			if(address.sameBoId(employee)) {
				employee.addAddress(address);
			}
		}
		lineNumber=dataLoader.getIndex();
		errorCollector.handleLinkageError(lineNumber,"Address in employee cannot be linked.", employee);
		lineNumber++;
	}
	
	public void setUpAssociations() {
		setUpLocationAssociations();
		setUpDepartmentAssociations();
		setUpJobAssociations();
		setUpPositionAssociations();
		setUpEmployeeAssociations();	
	}
}

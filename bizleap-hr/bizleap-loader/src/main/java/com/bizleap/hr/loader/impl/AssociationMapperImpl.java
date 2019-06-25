package com.bizleap.hr.loader.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	//private Map<Integer, ErrorCollection> errorHashMap;
	@Autowired
	private ErrorCollector errorCollector;
	@Autowired
	private DataLoader dataLoader;
	int lineNumber=0;
	
	private void addEmployeesToCompany(Company company) {
		for(Employee employee:dataManager.getEmployeeList()){
			if(employee.getWorkFor().isEqual(company.getBoId())) {
				company.addEmployee(employee);
			}
		}
	}

	private void setUpCompanyAssociations() {
		for(Company company:dataManager.getCompanyList()){
			addEmployeesToCompany(company);
			addLocationToCompany(company);
		}	
	}
	
	private void addCompanyToEmployee(Employee employee) {
		for(Company company:dataManager.getCompanyList()){
			if(company.sameBoId(employee.getWorkFor())){
				employee.setWorkFor(company);
				return;
			}
		}
		lineNumber=dataLoader.getIndex();
		errorCollector.handleLinkageError(lineNumber,"Company in employee cannot be linked.", employee);
		lineNumber++;
		dataLoader.setIndex(lineNumber);
	}
	
	
	private void setUpEmployeeAssociations() {
		for(Employee employee:dataManager.getEmployeeList()) {
			addCompanyToEmployee(employee);
			addAddressToEmployee(employee);
		}
	}
	
	private void addLocationToCompany(Company company) {
		
	}
	
	private void setUpLocationAssociations() {
		for(Location location:dataManager.getLocationList()) {
			addDepartmentToLocation(location);
		}
	}
	
	private void addDepartmentToLocation(Location location) {
		
	}
	
	private void setUpDepartmentAssociations() {
		for(Department department:dataManager.getDepartmentList()) {
			addJobToDepartment(department);
		}
	}
	
	private void addJobToDepartment(Department department) {
		
	}
	
	private void setUpJobAssociations() {
		for(Job job:dataManager.getJobList()) {
			addPositionToJob(job);
		}
	}
	private void addPositionToJob(Job job) {
		
	}
	
	private void setUpPositionAssociation() {
		for(Position position:dataManager.getPositionList()){
			addEmployeeToPosition(position);
		}
	}
	private void addEmployeeToPosition(Position position) {
		
	}
	
	private void addAddressToEmployee(Employee employee) {
		
	}
	public void setUpAssociations() {
		setUpCompanyAssociations();
		setUpEmployeeAssociations();
	}

//	public Map<Integer, ErrorCollection> getErrorHashMap() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

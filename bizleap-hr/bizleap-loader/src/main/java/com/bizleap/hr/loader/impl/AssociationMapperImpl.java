package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
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
import com.bizleap.hr.loader.DepartmentSaver;
import com.bizleap.hr.loader.ErrorHandler;

// @Author: San Thinzar Linn, Yamone Zin
@Service
public class AssociationMapperImpl implements AssociationMapper {
	
	private Logger logger = Logger.getLogger(AssociationMapperImpl.class);
	
	@Autowired
	private DataManager dataManager;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	private String toBoIDList(List<Department> departmentList) {
		String boIds="";
		for(Department department : departmentList) {
			boIds += department.getBoId()+",";
		}
		return boIds;
	}
	
	private void addDepartmentToLocation(Location location) {
		List<Department> sourceDepartmentList = location.getDepartmentList();
		
		if(CollectionUtils.isEmpty(sourceDepartmentList))
			return;

		List<Department> departmentList = new ArrayList<Department>();
		
		for(Department department : dataManager.getDepartmentList()) {
			for(Department locationDepartment : location.getDepartmentList()) {
				if(department.sameBoId(locationDepartment)) 
					departmentList.add(department);
			}
		}
		location.setDepartmentList(departmentList);

		if(sourceDepartmentList.size() != location.getDepartmentList().size()) 
			errorHandler.handleLinkageError("Departments in location cannot be linked. \n Department BoIds:"+toBoIDList(sourceDepartmentList), location);
	}
	
	private void setUpLocationAssociations() {
		for(Location location : dataManager.getLocationList()) {
			addDepartmentToLocation(location);
			logger.info("Location Association: "+ location);
		}
	}

	private void addJobToDepartment(Department department) {

		for(Job job : dataManager.getJobList()) {
			if(job.getDepartment().sameBoId(department))
				department.addJob(job);
		}
		//errorHandler.handleLinkageError("Job in department cannot be linked.", department);
	}
	
	private void addParentDepartment(Department department) {
		Department parentDept = department.getParentDepartment();
		logger.info("Parent Department: "+parentDept);
		if(parentDept.getName() == null) 
			return;
		
		for(Department parentDepartment : dataManager.getDepartmentList()) {
			if(parentDept.sameBoId(parentDepartment)) {
				department.setParentDepartment(parentDepartment);
				return;
			}
		}
		errorHandler.handleLinkageError("ParentDepartment in department cannot be linked.", department);
	}
	
	private void addLocationToDepartment(Department department) {
		
		for(Location location: dataManager.getLocationList()) {
			for(Department dept : location.getDepartmentList()) {
				if(department.sameBoId(dept)) {
					department.setLocation(location);
					return;
				}
			}
		}
		errorHandler.handleLinkageError("Location in department cannot be linked.", department);
	}
	
	private void setUpDepartmentAssociations() {
		for(Department department : dataManager.getDepartmentList()) {
			addJobToDepartment(department);
			addParentDepartment(department);
			addLocationToDepartment(department);
			logger.info("Department Association: "+ department);
		}
	}
	
	private void addPositionToJob(Job job) {
		for(Position position : dataManager.getPositionList()) {
			if(position.getJob().sameBoId(job)) 
				job.addPosition(position);
		}
		//errorHandler.handleLinkageError("Position in job cannot be linked.", job);
	}
	
	private void addDepartmentToJob(Job job) {
		Department sourceDept = job.getDepartment();
		if(sourceDept == null)
			return;

		for(Department department : dataManager.getDepartmentList()) {
			if(department.sameBoId(sourceDept)) {
				job.setDepartment(department);	
				return;
			}
		}
		errorHandler.handleLinkageError("Department in job cannot be linked.", job);
	}
	
	private void setUpJobAssociations() {
		for(Job job : dataManager.getJobList()) {
			addDepartmentToJob(job);
			addPositionToJob(job);
			logger.info("Job Association: "+ job);
		}
	}
	
	private void addJobToPosition(Position position) {
		Job sourceJob = position.getJob();
		if(sourceJob == null) 
			return;
		
		for(Job job : dataManager.getJobList()) {
			if(job.sameBoId(sourceJob)) {
				position.setJob(job);
				return;
			}
		}
		errorHandler.handleLinkageError("Job in position cannot be linked.", position);
	}
	
	private void addEmployeeToPosition(Position position) {
		for(Employee employee : dataManager.getEmployeeList()) {
			if(employee.getPosition().sameBoId(position)) {
				position.setEmployee(employee);
				return;
			}
		}
		errorHandler.handleLinkageError("Employee in position cannot be linked.", position);
	}
	
	private Position findPositionInList(Position target) {
		for(Position position : dataManager.getPositionList()) {
			if(target.sameBoId(position)) {
				return position;
			}
		}
		return null;
	}
	
	private void addReportToAndReportByPositions(Position target) {
		List<Position> reportToList = new ArrayList<Position>();
		List<Position> targetReportToList = target.getReportToList();
		if(targetReportToList.size()<=1) {
			return;
		}
		
		for(Position reportTo : targetReportToList) {
			Position realPosition = findPositionInList(reportTo);
			if(realPosition != null) {
				reportToList.add(realPosition);
				realPosition.addReportBy(target);
			}
			else errorHandler.handleLinkageError("Report Error: ", target);
		}
		target.setReportToList(reportToList);
	}
	
	private void setUpPositionAssociations() {
		for(Position position : dataManager.getPositionList()) {
			addJobToPosition(position);
			addEmployeeToPosition(position);
			addReportToAndReportByPositions(position);
			logger.info("Position Association: "+ position);
		}
	}
	
	private void addAddressToEmployee(Employee employee) {
		Address sourceAddress = employee.getAddress();
		if(sourceAddress == null) 
			return;
		
		for(Address address : dataManager.getAddressList()) {
			if(employee.getAddress().sameBoId(address)) {
				employee.setAddress(address);
				return;
			}
		}
		errorHandler.handleLinkageError("Address in employee cannot be linked.", employee);
	}
	
	private void addPositionToEmployee(Employee employee) {
		Position sourcePosition = employee.getPosition();
		if(sourcePosition == null)
			return;
		
		for(Position position : dataManager.getPositionList()) {
			if(position.sameBoId(employee.getPosition())) {
				employee.setPosition(position);
				return;
			}
		}
		errorHandler.handleLinkageError("Position in employee cannot be linked.", employee);
	}
	
	private void setUpEmployeeAssociations() {
		for(Employee employee : dataManager.getEmployeeList()) {
			addPositionToEmployee(employee);
			addAddressToEmployee(employee);
			logger.info("Employee Association: "+ employee);
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
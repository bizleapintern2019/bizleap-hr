package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.loader.AddressSaver;
import com.bizleap.hr.loader.AssociationMapper;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.DepartmentSaver;
import com.bizleap.hr.loader.EmployeeSaver;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.loader.JobSaver;
import com.bizleap.hr.loader.LocationSaver;
import com.bizleap.hr.loader.PositionSaver;

//@Author: Khin Chanmyae Thu 
@Service
public class DataManagerImpl implements DataManager {
	@Autowired
	private DataLoaderImpl dataLoader;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private AssociationMapper associationMapper;
	
//	@Autowired
//	private SaverJDBC saver;
	
	@Autowired
	private LocationSaver locationSaver;
	
	@Autowired 
	private DepartmentSaver departmentSaver;
	
	@Autowired
	private JobSaver jobSaver;
	
	@Autowired
	private PositionSaver positionSaver;
	
	@Autowired
	private EmployeeSaver employeeSaver;
	
	@Autowired
	private AddressSaver addressSaver;
	
	private Map<Integer,Error> errorMap = new HashMap<Integer,Error>();
	private List<Employee> employeeList;
	private List<Department> departmentList;
	private List<Job> jobList;
	private List<Location> locationList;
	private List<Position> positionList;
	private List<Address> addressList;
	private Logger logger = Logger.getLogger(DataManagerImpl.class);
	
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList=employeeList;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}
	
	public void loadData() {
		try {
			employeeList = dataLoader.loadEmployee();
			departmentList= dataLoader.loadDepartment();
			jobList = dataLoader.loadJob();
			locationList = dataLoader.loadLocation();
			positionList = dataLoader.loadPosition();
			} catch (Exception e) {
				logger.error(e);
			}
	}
	
//	public void saveData() {
//		if(errorCollector.hasError())
//			return;
//		for(Company company: getCompanyList()) {
//			if(company!=null) {
//				saver.saveCompany(company);
//			}
//		}
//		for(Employee employee: getEmployeeList()) {
//			if(employee!=null) {
//				saver.saveEmployee(employee);
//			}
//		}
//	}
	
	public void saveData() {
		try {
			locationSaver.setLocationList(locationList);
			locationSaver.savePass1();
			
			departmentSaver.setDepartmentList(departmentList);
			departmentSaver.savePass1();
			
			jobSaver.setJobList(jobList);
			jobSaver.savePass1();
			
			positionSaver.setPositionList(positionList);
			positionSaver.savePass1();
			
			employeeSaver.setEmployeeList(employeeList);
			employeeSaver.savePass1();
			
			addressSaver.setAddressList(addressList);
			addressSaver.savePass1();
			
		} catch (ServiceUnavailableException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void associateData() {
		if(!errorHandler.hasError()) 
			associationMapper.setUpAssociations();
	}
	
	public void load() {
		loadData();
		associateData();
		saveData();
		if(errorHandler.hasError()) {
			logger.error("Error Occur. Error map is "+ errorHandler.getErrorMap());
		}
	}
}

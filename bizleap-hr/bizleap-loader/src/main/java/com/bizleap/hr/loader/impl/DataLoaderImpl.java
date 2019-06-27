package com.bizleap.hr.loader.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Error;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Location;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.DataLoader;
import com.bizleap.hr.loader.ErrorHandler;
import com.bizleap.hr.loader.FileLoader;

// @Author: Kaung Pyae Sone Htun
@Service
public class DataLoaderImpl implements DataLoader {
	@Autowired
	private FileLoader fileLoader;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	public Map<Integer,Error> errorMap;
	public int index = 1;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Employee> loadEmployee() throws Exception {
		fileLoader.start("C:\\Users\\DELL\\bizleap-hr-workspace\\bizleap-project\\data_file\\employee.txt");
		String dataLine = "";
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasMore())
						dataLine = fileLoader.getLine();
				}
				employee = Employee.parseEmployee(dataLine);
				if(employee != null) {
					employeeList.add(employee);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(fileLoader.getLineNumber(),"Employee file loading.",dataLine);
			}
		}
		fileLoader.finish();
		return employeeList;
	}

	
	public List<Location> loadLocation() throws Exception {
		fileLoader.start("C:\\Users\\DELL\\bizleap-hr-workspace\\bizleap-project\\data_file\\location.txt");
		String dataLine = "";
		List<Location> locationList = new ArrayList<Location>();
		Location location = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasMore())
						dataLine = fileLoader.getLine();
				}
				location = Location.parseLocation(dataLine);
				if(location != null) {
					locationList.add(location);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(fileLoader.getLineNumber(),"Location file loading.",dataLine);
			}
		}
		fileLoader.finish();
		return locationList;
	}
	
	public List<Department> loadDepartment() throws Exception {
		fileLoader.start("C:\\Users\\DELL\\bizleap-hr-workspace\\bizleap-project\\data_file\\department.txt");
		String dataLine = "";
		List<Department> departmentList = new ArrayList<Department>();
		Department department = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasMore())
						dataLine = fileLoader.getLine();
				}
				department = Department.parseDepartment(dataLine);
				if(department != null) {
					departmentList.add(department);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(fileLoader.getLineNumber(),"Department file loading.",dataLine);
			}
		}
		fileLoader.finish();
		return departmentList;
	}
	
	public List<Job> loadJob() throws Exception {
		fileLoader.start("C:\\Users\\DELL\\bizleap-hr-workspace\\bizleap-project\\data_file\\job.txt");
		String dataLine = "";
		List<Job> jobList = new ArrayList<Job>();
		Job job = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasMore())
						dataLine = fileLoader.getLine();
				}
				job = Job.parseJob(dataLine);
				if(job != null) {
					jobList.add(job);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(fileLoader.getLineNumber(),"Job file loading.",dataLine);
			}
		}
		fileLoader.finish();
		return jobList;
	}
	
	public List<Position> loadPosition() throws Exception {
		fileLoader.start("C:\\Users\\DELL\\bizleap-hr-workspace\\bizleap-project\\data_file\\position.txt");
		String dataLine = "";
		List<Position> positionList = new ArrayList<Position>();
		Position position = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasMore())
						dataLine = fileLoader.getLine();
				}
				position = Position.parsePosition(dataLine);
				if(position != null) {
					positionList.add(position);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(fileLoader.getLineNumber(),"Position file loading.",dataLine);
			}
		}
		fileLoader.finish();
		return positionList;
	}
	
	public List<Address> loadAddress() throws Exception {
		fileLoader.start("C:\\Users\\DELL\\bizleap-hr-workspace\\bizleap-project\\data_file\\address.txt");
		String dataLine = "";
		List<Address> addressList = new ArrayList<Address>();
		Address address = null;
		while(fileLoader.hasMore()) {
			try {
				dataLine = fileLoader.getLine();
				if(dataLine.startsWith("#")) {
					if(fileLoader.hasMore())
						dataLine = fileLoader.getLine();
				}
				address = Address.parseAddress(dataLine);
				if(address != null) {
					addressList.add(address);
				}
			}catch (Exception e) {
				errorHandler.handleLoadingError(fileLoader.getLineNumber(),"Address file loading.",dataLine);	
			}
		}
		fileLoader.finish();
		return addressList;
	}
}
package com.bizleap.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.EmployeeDao;
import com.bizleap.service.EmployeeService;

//@Author: Nyan Lin Htet
@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional(readOnly = false)
	public void saveEmployee(Employee employee) throws IOException, ServiceUnavailableException {
		employeeDao.save(employee);
	}
	
	@Transactional(readOnly = true)
	public List<AbstractEntity> getAllEntity() throws ServiceUnavailableException {
		List<Employee> employeeList = employeeDao.getAll("from Employee");
		if(!CollectionUtils.isEmpty(employeeList)) {
			hibernateInitializedList(employeeList);	

			List<AbstractEntity> entityList = new ArrayList<AbstractEntity>();
			entityList.addAll(getAll());
			return entityList;
		}
		return null;
	}

	public List<Employee> getAll() throws ServiceUnavailableException {
		List<Employee> employeeList = employeeDao.getAll("from Employee employee");
		if(employeeList.size() != 0 || employeeList != null) {
			hibernateInitializedList(employeeList);
			return employeeList;
		}
		return null;
	}

	public Employee findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.boId=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, boId);
		if(employeeList.size() != 0 || employeeList != null) {
			hibernateInitializedEmployee(employeeList.get(0));
			return employeeList.get(0);
		}
		return null;
	}
	
	public List<Employee> findByFirstName(String firstName) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.firstName=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, firstName);
		if(employeeList.size() != 0 || employeeList != null) {
			hibernateInitializedList(employeeList);
			return employeeList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Employee> findByLastName(String lastName) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.lastName=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, lastName);

		if(employeeList.size() != 0 || employeeList != null) {
			hibernateInitializedList(employeeList);
			return employeeList;
		}
		return null;
	}

	
	public List<Employee> findByGender(String gender) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.gender=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, gender);

		if(employeeList.size() != 0 || employeeList != null) {

			hibernateInitializedList(employeeList);
			return employeeList;
		}
		return null;
	}

	public void hibernateInitializedEmployee(Employee employee) {
		Hibernate.initialize(employee);
	}

	public void hibernateInitializedList(List<Employee> employeeList) {
		for(Employee employee : employeeList) {
			hibernateInitializedEmployee(employee);	
		}
	}
}
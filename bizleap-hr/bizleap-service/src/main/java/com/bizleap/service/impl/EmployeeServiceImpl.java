package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Department;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.EmployeeDao;
import com.bizleap.service.EmployeeService;

//@Author: Nyan Lin Htet
@Service
//@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	public void saveEmployee(Employee employee) throws IOException, ServiceUnavailableException {
		employeeDao.save(employee);
	}

	public List<Employee> getAll() throws ServiceUnavailableException {

		List<Employee> employeeList = employeeDao.getAll("from Employee employee");
		return employeeList;
	}

	public List<Employee> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.boId=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, boId);
		return employeeList;
	}
	
	public List<Employee> findByFirstName(String firstName) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.firstName=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, firstName);
		return employeeList;
	}
	
	public List<Employee> findByLastName(String lastName) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.lastName=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, lastName);
		return employeeList;
	}

	public List<Employee> findByGender(String gender) throws ServiceUnavailableException {

		String query = "from Employee employee where employee.gender=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, gender);
		return employeeList;
	}
	
	public void hibernateInitializedEmployee(Employee employee) {
		Hibernate.initialize(employee);
		Hibernate.initialize(employee.getAddress());
		Hibernate.initialize(employee.getPosition());
	}
	
	public void hibernateInitializedList(List<Employee> employeeList) {
		for(Employee employee : employeeList) {
			hibernateInitializedEmployee(employee);	
		}
	}
}
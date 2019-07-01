package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
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

		List<Employee> employeeList = employeeDao.getAll("From Employee employee");
		return employeeList;
	}

	public List<Employee> findByBoId(String boId) throws ServiceUnavailableException {

		String query = "select employee from Employee employee where employee.boId=:dataInput";
		List<Employee> employeeList = employeeDao.findByString(query, boId);
		return employeeList;
	}
}
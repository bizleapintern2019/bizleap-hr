package com.bizleap.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.JDBCService;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	JDBCService jdbcservice;
	
	@Override
	public void saveEmployee(Employee employee) {
		Connection connection = null;
		if(employee == null) 
			return;
		
		PreparedStatement statement;
		
		try {
			connection = jdbcservice.getJDBCConnection();
			statement = connection.prepareStatement("insert into employee" + "(boId,firstName,lastName,age,title,salary,email,phoneNumber,companyBoId)" + "values(?,?,?,?,?,?,?,?,?)");
			
			statement.setString(1, employee.getBoId());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setInt(4, employee.getAge());
			statement.setString(5, employee.getTitle());
			statement.setInt(6, employee.getSalary());
			statement.setString(7, employee.getEmail());
			statement.setString(8, employee.getPhone());
			
			if(employee.getWorkFor() != null)
				statement.setString(9, employee.getWorkForBoId());
			
			statement.executeUpdate();
			statement.close();
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
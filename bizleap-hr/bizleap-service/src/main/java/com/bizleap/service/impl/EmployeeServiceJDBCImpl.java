package com.bizleap.service.impl;

import java.sql.SQLException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EmployeeServiceImpl implements EmployeeService {
	JDBCService jdbcService = new JDBCServiceImpl();
	@Override
	public void saveEmployee(Employee employee) {
		Connection connection= null;
		if(employee == null)
			return;
		connection = jdbcService.getJDBCConnection();
		PreparedStatement pStatement;
		try {
			pStatement= (PreparedStatement) connection.prepareStatement("insert into employee"+
			"(boId,firstName,lastName,age,title,salary,email,phoneNumber,companyBoId)"+"values(?,?,?,?,?,?,?,?,?)");
			pStatement.setString(1, employee.getBoId());
			pStatement.setString(2, employee.getFirstName());
			pStatement.setString(3, employee.getLastName());
			pStatement.setInt(4, employee.getAge());
			pStatement.setString(5, employee.getTitle());
			pStatement.setInt(6, employee.getSalary());
			pStatement.setString(7, employee.getEmail());
			pStatement.setString(8, employee.getPhone());
			if(employee.getWorkFor()!=null)
				pStatement.setString(9, employee.getWorkForBoId());
			
			pStatement.executeUpdate();
			pStatement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

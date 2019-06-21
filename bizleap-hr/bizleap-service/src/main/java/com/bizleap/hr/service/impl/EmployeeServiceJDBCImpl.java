package com.bizleap.hr.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.service.EmployeeServiceJDBC;
import com.bizleap.hr.service.JDBCService;

import java.sql.PreparedStatement;
import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
@Service
public class EmployeeServiceJDBCImpl implements EmployeeServiceJDBC {

	@Autowired
	private JDBCService jdbcService;

	@Override
	public void saveEmployee(Employee employee) {
		Connection connection=null;
		if(employee == null)
			return;
		connection = jdbcService.getJDBCConnection();
		
		try {
			PreparedStatement pStatement = connection.prepareStatement("insert into employee"+"(boId,firstName,lastName,age,title,salary,email,phoneNumber,companyBoId)"+"values(?,?,?,?,?,?,?,?,?)");
			pStatement.setString(1, employee.getBoId());
			pStatement.setString(2, employee.getFirstName());
			pStatement.setString(3, employee.getLastName());
			pStatement.setInt	(4, employee.getAge());
			pStatement.setString(5, employee.getTitle());
			pStatement.setInt	(6, employee.getSalary());
			pStatement.setString(7, employee.getEmail());
			pStatement.setString(8, employee.getPhone());
			if(employee.getWorkFor() != null) {
				pStatement.setString(9, employee.getWorkFor().getBoId());
			}
			pStatement.executeUpdate();
			pStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package com.bizleap.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Service
public class EmployeeServiceJDBCImpl implements EmployeeServiceJDBC {
	@Autowired
	private JDBCService jdbcService;
	
//	@Override
//	public void saveEmployee(Employee employee) {
//		Connection connection= null;
//		if(employee == null)
//			return;
//		connection = jdbcService.getJDBCConnection();
//		PreparedStatement pStatement;
//		try {
//			pStatement= (PreparedStatement) connection.prepareStatement("insert into employee"+
//			"(BOID,firstName,lastName,age,title,PhoneNumber,salary,gmail,companyBOID)"+"values(?,?,?,?,?,?,?,?,?)");
//			pStatement.setString(1, employee.getBoId());
//			pStatement.setString(2, employee.getFirstName());
//			pStatement.setString(3, employee.getLastName());
//			pStatement.setInt(4, employee.getAge());
//			pStatement.setString(5, employee.getTitle());
//			pStatement.setString(6, employee.getPhone());
//			pStatement.setInt(7, employee.getSalary());
//			pStatement.setString(8, employee.getEmail());
//			
//			if(employee.getWorkFor()!=null)
//				pStatement.setString(9, employee.getWorkForBoId());			
//			pStatement.executeUpdate();
//			pStatement.close();
//			connection.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}

package com.bizleap.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.PreparedStatement;

@Service
public class EmployeeServiceJDBCImpl implements EmployeeServiceJDBC {

	JDBCService jdbcService = new JDBCServiceImpl();

	@Override
	public void saveEmployee(Employee employee) {
		Connection connection = null;
		if (employee == null)
			return;
		connection = jdbcService.getJDBCconnection();
		if (connection == null) {
			System.out.println("Connection Fail");
		}
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(
					"insert into employee " + "(boId,firstName,lastName,age,salary,email,title,phoneNo,comBoId)"
							+ " values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, employee.getBoId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setInt(4, employee.getAge());
			ps.setInt(5, employee.getSalary());
			ps.setString(6, employee.getEmail());
			ps.setString(7, employee.getTitle());
			ps.setString(8, employee.getPhone());
			if (employee.getWorkFor() != null)
				ps.setString(9, employee.getWorkFor().getBoId());
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}

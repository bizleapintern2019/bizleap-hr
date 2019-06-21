package com.bizleap.service.Impl;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeServiceJDBC;
import com.bizleap.service.JDBCService;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
public class EmployeeServiceJDBCImpl implements EmployeeServiceJDBC {

	private JDBCService jdbcService =new JDBCServiceImpl();

	@Override
	public void saveEmployee(Employee employee) {

			if (employee == null)
				return;
			PreparedStatement ps;
			Connection connection = null;
			try {
				connection = jdbcService.getJDBCConnection();
				ps = connection.prepareStatement("insert into employee"
						+ "(boId,firstName,lastName,age,title,salary,email,phoneNumber,companyBoId)"
						+ "values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, employee.getBoId());
				ps.setString(2, employee.getFirstname());
				ps.setString(3, employee.getLastname());
				ps.setInt(4, employee.getAge());
				ps.setString(5, employee.getTitle());
				ps.setInt(6, employee.getSalary());
				ps.setString(7, employee.getEmail());
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
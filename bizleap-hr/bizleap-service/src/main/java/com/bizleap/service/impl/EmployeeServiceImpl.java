/**
 * 
 */
package com.bizleap.service.impl;

import java.sql.SQLException;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EmployeeServiceImpl implements EmployeeService {

	JDBCService jdbcService=new JDBCServiceImpl();
	@Override
	public void saveEmployee(Employee employee) {
		Connection connection=null;
		if(employee ==null)
			return;
		PreparedStatement ps;
		try{
			connection=jdbcService.getJDBCConnection();
			ps=(PreparedStatement) connection.prepareStatement("insert into employee"
					+"( boId,firstName,lastName,age,title,salary,email,phone,companyBoId)"
					+"values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, employee.getBoId());
			ps.setString(2,employee.getFirstName());
			ps.setString(3,employee.getLastName());
			ps.setInt(4, employee.getAge());
			ps.setString(5,employee.getTitle());
			ps.setInt(6,employee.getSalary());
			ps.setString(7,  employee.getEmail());
			ps.setString(8, employee.getPhone());
			if(employee.getWorkFor()!=null)
				ps.setString(9, employee.getWorkFor().getBoId());
			ps.executeUpdate();
			ps.close();
			connection.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}

}

package com.bizleap.hr.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.hr.service.CompanyServiceJDBC;
import com.bizleap.hr.service.JDBCService;

@Service
public class CompanyServiceJDBCImpl implements CompanyServiceJDBC {
	
	JDBCService jdbcService = new JDBCServiceImpl();

	public void saveCompany(Company company) {

		Connection connection = null;
		
		if(company == null)
			return;

		PreparedStatement statement;

		try {

			connection = jdbcService.getJDBCConnection();

			statement =  connection.prepareStatement("insert into company " 
					+ "(boId, name, address, phoneNumber, email, ceo)"
					+ "values(?, ?, ?, ?, ?, ?)");

			statement.setString(1, company.getBoId());
			statement.setString(2, company.getCompanyName());
			statement.setString(3, company.getAddress());
			statement.setString(4, company.getPhone());
			statement.setString(5, company.getEmail());
			statement.setString(6, company.getCeo());

			statement.executeUpdate();
			statement.close();
			connection.close();
		} 

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
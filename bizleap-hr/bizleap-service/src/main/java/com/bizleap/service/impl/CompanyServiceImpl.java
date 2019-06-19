package com.bizleap.service.impl;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.JDBCService;

public class CompanyServiceImpl implements CompanyService {

	JDBCService jdbcService = new JDBCServiceImpl();
	@Override
	public void saveCompany(Company company) {
		Connection connection = null;
		
		if(company == null)
			return;
		PreparedStatement statement;
		
		try{
			connection = jdbcService.getJDBCConnection();
			statement = (PreparedStatement)connection.prepareStatement("insert into company "
					+ "(boId,name,address,phoneNumber,email,ceo)"
					+ "values(?,?,?,?,?,?)");
			
			statement.setString(1, company.getBoId());
			statement.setString(2, company.getName());
			statement.setString(3, company.getAddress());
			statement.setString(4, company.getPhone());
			statement.setString(5, company.getEmail());
			statement.setString(6, company.getCeo());
			
			statement.executeUpdate();
			statement.close();
			connection.close();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

}

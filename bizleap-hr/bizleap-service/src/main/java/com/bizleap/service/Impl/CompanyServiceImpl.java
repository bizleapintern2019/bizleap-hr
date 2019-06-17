package com.bizleap.service.Impl;

import java.sql.SQLException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CompanyServiceImpl implements CompanyService {

	JDBCService jdbcService=new JDBCServiceImpl();
	@Override
	public void saveCompany(Company company) {
		Connection connection=null;
		try {
			connection=jdbcService.getJDBCConnection();
			PreparedStatement ps= (PreparedStatement) connection.prepareStatement("insert into company"+"(boId,name,address,phoneNumber,email,ceo)"+"values(?,?,?,?,?,?)");
			ps.setString(1, company.getBoId());
			ps.setString(2, company.getCompanyName());
			ps.setString(3,company.getAddress());
			ps.setString(4, company.getPhone());
			ps.setString(5, company.getEmail());
			ps.setString(6, company.getCeo());
			
			ps.executeUpdate();
			ps.close();
			connection.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}

package com.bizleap.service.impl;

import java.sql.SQLException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.JDBCService;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CompanyServiceImpl implements CompanyService {
	JDBCService jdbcService=new JDBCServiceImpl();
	@Override
	public void saveCompany(Company company) {
		Connection connection=null;
		if(company ==null)
			return;
		PreparedStatement ps;
		try{
			connection=jdbcService.getJDBCConnection();
			ps= connection.prepareStatement("insert into company"+"(boId,name,address,phone,email,ceo)"+"values(?,?,?,?,?,?)");
			ps.setString(1, company.getBoId());
			ps.setString(2, company.getName());
			ps.setString(3, company.getAddress());
			ps.setString(4, company.getPhone());
			ps.setString(5, company.getEmail());
			ps.setString(6, company.getCeo());
			
			ps.executeUpdate();
			ps.close();
			connection.close();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}

}

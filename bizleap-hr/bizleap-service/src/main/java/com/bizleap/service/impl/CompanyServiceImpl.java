package com.bizleap.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.JDBCService;
import java.sql.PreparedStatement;;

public class CompanyServiceImpl implements CompanyService {
	JDBCService jdbcService = new JDBCServiceImpl();
	@Override
	public void saveCompany(Company company) {
		Connection connection =null;
		if(company==null)
			return;
		connection= jdbcService.getJDBCconnection();
		if(connection==null) {
			System.out.println("Connection Fail");
		}
		try {
			PreparedStatement ps = connection.prepareStatement("insert into company "+"(boid,name,address,phoneNo,email,ceo)"+" values (?,?,?,?,?,?)");
			ps.setString(1, company.getBoId());
			ps.setString(2, company.getCompanyName());
			ps.setString(3, company.getAddress());
			ps.setString(4, company.getPhone());
			ps.setString(5, company.getEmail());
			ps.setString(6, company.getCeo());
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	}
}
		

	@Override
	public void openJBBCconnection() {
		// TODO Auto-generated method stub
		
	}

}

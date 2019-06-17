package com.bizleap.service.impl;

import com.bizleap.commons.domain.entity.Company;
import com.bizleap.service.CompanyService;
import com.bizleap.service.JDBCService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CompanyServiceImpl implements CompanyService {

	JDBCService jdbcService = new JDBCServiceImpl();

	@Override
	public void saveCompany(Company comployee) {
		if (comployee == null)
			return;
		Connection connection = null;
		PreparedStatement ps;
		try {
			connection = jdbcService.getJDBConnection();
			ps = (PreparedStatement) connection.prepareStatement(
					"insert into company" + "(boId,name,address,phoneNumber,email,ceo)"
							+ " values(?,?,?,?,?,?)");

			ps.setString(1, comployee.getBoId());
			ps.setString(2, comployee.getName());
			ps.setString(3, comployee.getAddress());
			ps.setString(4, comployee.getPhone());
			ps.setString(5, comployee.getEmail());
			ps.setString(6, comployee.getCeo());

			ps.executeUpdate();
			ps.close();	
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

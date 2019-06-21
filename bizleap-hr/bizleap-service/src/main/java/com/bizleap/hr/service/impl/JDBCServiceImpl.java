package com.bizleap.hr.service.impl;

import java.sql.DriverManager;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizleap.hr.service.JDBCService;
import com.mysql.jdbc.Connection;

@Service
public class JDBCServiceImpl implements JDBCService {
	
	private Logger logger = Logger.getLogger(JDBCServiceImpl.class);
	
	@Override
	public Connection getJDBCConnection() {
		Connection connection = null;
		String dBName="internhr";
		String userName="root";
		String password="root";
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection =(Connection)DriverManager
						.getConnection("jdbc:mysql://localhost/"+dBName,userName,password);
				if(connection == null) {
					logger.info("Connection Null");
				} else {
					logger.info("Connection is not null");
				}
			} catch(ClassNotFoundException e) {
				logger.info(e.getMessage());
			}
		} catch(SQLException e) {
			logger.info(e.getMessage());
		}
		return connection;
	}
}

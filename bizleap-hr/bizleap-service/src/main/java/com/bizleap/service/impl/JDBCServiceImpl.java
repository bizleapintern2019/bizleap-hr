//package com.bizleap.service.impl;
//
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import org.junit.Ignore;
//import org.springframework.stereotype.Service;
//
//import com.bizleap.service.JDBCService;
//import java.sql.Connection;
//
//@Ignore
//public class JDBCServiceImpl implements JDBCService{
//
//	@Override
//	public Connection getJDBCconnection() {
//		Connection connection=null;
//		String dBName="internhr";
//		String userName="root";
//		String password="root";
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//				connection=(Connection)DriverManager.getConnection("jdbc:mysql://localhost/"+dBName+"?user="+userName+"&password="+password);
//			}catch(ClassNotFoundException e) {
//				System.out.println(e.getMessage());
//			}catch(SQLException e) {
//				System.out.println(e.getMessage());
//			}
//			return connection;
//	}
//}

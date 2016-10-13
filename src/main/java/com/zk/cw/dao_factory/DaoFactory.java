package com.zk.cw.dao_factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	//private static String url = "jdbc:mysql://localhost:3306/";
	private static String url = "jdbc:mysql://10.5.0.70:3306/cw?useUnicode=true&characterEncoding=UTF-8";
	private static String driver = "com.mysql.jdbc.Driver";
	//private static String user = "root";
	private static String user = "zkaya";
	//private static String password = "kaya";
	private static String password = "";
	
	public static Connection openConnection() {   
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			System.err.println(ex.getMessage());				
		}
		return null;		  
	}
}

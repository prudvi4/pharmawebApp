package com.excelr.pharma.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excelr.pharma.exceptions.PharmaException;

/**
 * 
 */
public final class DBConnectionUtility {
	private DBConnectionUtility() {

	}

	/**
	 * @return Connection
	 * @throws PharmaException
	 */
	public static Connection getConnection() throws PharmaException {
		Connection connection = null;
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/?user=excelr";
		String user = "excelr";
		String pass = "excelr@123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, pass);
			// System.out.println("Connection Success..Start the Operations..!!!");
		} catch (SQLException e) {
			throw new PharmaException(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}

package com.eCart.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseConnector {
	Connection con;

	public Connection DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecart", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}

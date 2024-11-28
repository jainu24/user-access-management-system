package com.leucine.tech.uams.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;

public class DatabaseConnection {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/uams_db";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "root";

	static {
		try {
			// Explicitly load the PostgreSQL driver
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("PostgreSQL Driver not found", e);
		}
	}

	public static Connection getConnection() throws SQLException, UserAccessManagementSystemException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			throw new UserAccessManagementSystemException(AppConstants.FAILED_TO_GET_DB_CONNECTION);
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

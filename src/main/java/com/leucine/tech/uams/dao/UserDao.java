package com.leucine.tech.uams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.constants.SqlQuery;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;
import com.leucine.tech.uams.utils.DatabaseConnection;
import com.leucine.tech.uams.utils.PasswordUtil;

public class UserDao {

	public void registerNewUser(String userName, String password, String role)
			throws UserAccessManagementSystemException {
		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement userCountStatement = conn.prepareStatement(SqlQuery.USER_COUNT_QUERY);
			userCountStatement.setString(1, userName);
			ResultSet rs = userCountStatement.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(AppConstants.USER_COUNT);
			}
			if (count > 0) {
				throw new UserAccessManagementSystemException(String.format(AppConstants.USER_NAME_EXIST, userName));
			} else {
				PreparedStatement stmt = conn.prepareStatement(SqlQuery.USER_REGISTRATION_QUERY);
				stmt.setString(1, userName);
				stmt.setString(2, PasswordUtil.hashPassword(password));
				stmt.setString(3, role);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			throw new UserAccessManagementSystemException(AppConstants.FAILED_TO_REGISTER_USER);
		}
	}

	public String getAccessRole(HttpServletRequest request) throws UserAccessManagementSystemException {
		String username = request.getParameter(AppConstants.USER_NAME);
		String password = request.getParameter(AppConstants.PASSWORD);
		String role = null;
		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.USER_ROLE_QUERY);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String hashPasword = rs.getString(AppConstants.PASSWORD);
				if (PasswordUtil.verifyPassword(password, hashPasword)) {
					int userId = rs.getInt(AppConstants.ID);
					role = rs.getString(AppConstants.ROLE);
					HttpSession session = request.getSession();
					session.setAttribute(AppConstants.USER_ID, userId);
					session.setAttribute(AppConstants.ROLE, role);
				} else {
					throw new UserAccessManagementSystemException(AppConstants.USER_NAME_PASSWORD_INVALID);
				}
			} else if (role == null) {
				throw new UserAccessManagementSystemException(String.format(AppConstants.USER_NOT_REGISTERED, username));
			}
		} catch (SQLException e) {
				throw new UserAccessManagementSystemException(String.format(AppConstants.USER_NOT_REGISTERED, username));
		}
		return role;
	}

}

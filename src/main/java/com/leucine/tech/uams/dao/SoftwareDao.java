package com.leucine.tech.uams.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.constants.SqlQuery;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;
import com.leucine.tech.uams.models.Software;
import com.leucine.tech.uams.utils.DatabaseConnection;

public class SoftwareDao {

	public List<Software> getSoftwareList() throws UserAccessManagementSystemException {
		List<Software> softwareList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.SELECT_SOFTWARE_LIST);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(AppConstants.ID);
				String name = rs.getString(AppConstants.NAME);
				softwareList.add(new Software(id, name));
			}
			if (softwareList.isEmpty()) {
				throw new UserAccessManagementSystemException(String.format(AppConstants.SOFTWARE_LIST_EMPTY));
			}
		} catch (SQLException e) {
			throw new UserAccessManagementSystemException(AppConstants.FAILED_TO_GET_DB_CONNECTION);
		}
		return softwareList;
	}

	public void createSoftwareRequest(Integer userId, String softwareId, String accessType, String reason)
			throws UserAccessManagementSystemException {
		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.REQUEST_CREATED_SUCCESSFULLY);
			stmt.setInt(1, userId);
			stmt.setInt(2, Integer.parseInt(softwareId));
			stmt.setString(3, accessType);
			stmt.setString(4, reason);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new UserAccessManagementSystemException(
					String.format(AppConstants.FAILED_TO_CREATE_SOFTWARE_REQUEST, e.getMessage()));
		}
	}
}

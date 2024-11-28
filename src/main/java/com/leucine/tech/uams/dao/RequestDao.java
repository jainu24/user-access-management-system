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
import com.leucine.tech.uams.models.AccessRequest;
import com.leucine.tech.uams.utils.DatabaseConnection;

/**
 * @author Jainu
 */
public class RequestDao {

	/**
	 * This method used to fetch all the pending request as List
	 * 
	 * @throws UserAccessManagementSystemException
	 */
	public List<AccessRequest> fetchPendingRequests() throws UserAccessManagementSystemException {
		List<AccessRequest> pendingRequestsList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.SELECT_SOFTWARE_REQUEST_PENDING_LIST);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				AccessRequest requestObj = new AccessRequest();
				requestObj.setId(rs.getInt(AppConstants.ID));
				requestObj.setUsername(rs.getString(AppConstants.USER_NAME));
				requestObj.setSoftwareName(rs.getString(AppConstants.SOFTWARE_NAME));
				requestObj.setAccessType(rs.getString(AppConstants.ACCESS_TYPE_COLUMN));
				requestObj.setStatus(rs.getString(AppConstants.STATUS));
				requestObj.setReason(rs.getString(AppConstants.REASON));
				pendingRequestsList.add(requestObj);
			}
			if (pendingRequestsList.isEmpty()) {
				throw new UserAccessManagementSystemException(AppConstants.NO_PENDING_REQUEST);
			}
		} catch (SQLException e) {
			throw new UserAccessManagementSystemException(AppConstants.FAILED_TO_GET_DB_CONNECTION);
		}
		return pendingRequestsList;
	}

	/**
	 * This method will update the Approve or Reject status based on the Request Id.
	 * 
	 * @param requestId
	 * @param status
	 * @throws UserAccessManagementSystemException
	 */
	public void updateRequestStatus(String requestId, String status) throws UserAccessManagementSystemException {
		try (Connection conn = DatabaseConnection.getConnection()) {
			// Update the request status
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.UPDATE_APPROVAL_STATUS);
			stmt.setString(1, status);
			stmt.setInt(2, Integer.parseInt(requestId));
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new UserAccessManagementSystemException(
					String.format(AppConstants.FAILED_TO_GET_DB_CONNECTION, status));
		}

	}

}

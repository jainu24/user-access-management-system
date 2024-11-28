package com.leucine.tech.uams.constants;

public class SqlQuery {
	
	public static final String SELECT_SOFTWARE_LIST = "SELECT id, name FROM software";

	public static final String SELECT_ACCESS_REQUEST_LIST = "select * From requests where status='Pending'";

	public static final String UPDATE_APPROVAL_STATUS = "UPDATE requests SET status = ? WHERE id = ?";

	public static final String SELECT_SOFTWARE_REQUEST_PENDING_LIST = "SELECT r.id, u.username, s.name AS softwareName, r.access_type, r.status, r.reason FROM users u, software s, requests r WHERE r.user_id = u.id AND r.software_id = s.id AND r.status = 'Pending'";

	public static final String REQUEST_CREATED_SUCCESSFULLY = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";

	public static final String USER_COUNT_QUERY = "SELECT COUNT(*) AS user_count FROM users WHERE username = ?";
	
	public static final String USER_REGISTRATION_QUERY="INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
	
	public static final String USER_ROLE_QUERY ="SELECT id, role, password FROM users WHERE username = ?";
}

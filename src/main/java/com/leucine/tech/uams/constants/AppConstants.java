package com.leucine.tech.uams.constants;

public interface AppConstants {

	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String SUCCESS_MESSAGE = "successMessage";

	// JSP PAGE NAMES:

	public static final String PENDING_REQUEST_JSP_PAGE = "pendingRequest.jsp";
	public static final String REQUEST_ACCESS_JSP_PAGE = "requestAccess.jsp";
	public static final String LOGIN_JSP_PAGE_URI = "/login.jsp";
	public static final String CREATE_SOFTWARE_JSP_PAGE = "createSoftware.jsp";
	public static final String LOGIN_JSP_PAGE = "login.jsp";
	public static final String SIGNUP_JSP_PAGE_URI = "/signup.jsp";

	// Application Messages

	public static final String NO_PENDING_REQUEST = "Currently there is no pending requests.";
	public static final String FAILED_TO_UPDATE_APPROV_REJECT_STATUS = "Failed to %s the request.";
	public static final String UPDATE_APPROV_REJECT_STATUS = "Successfully %s the request.";
	public static final String REQUEST_APPROV_SUCCESSFULLY = "Request created Successfully.";
	public static final String FAILED_TO_CREATE_SOFTWARE_REQUEST = "Failed to create request due to %s ";
	public static final String FAILED_TO_REGISTER_USER = "Failed to register the user try after some time.";
	public static final String USER_NAME_EXIST = "User name: %s Already register with our database, Try with other user name.";
	public static final String USER_REGISTRATION_COMPLETED = "Hi %s Your registration completed, You can able to access the UAMS site.";
	public static final String SOFTWARE_LIST_EMPTY = "Software List is not available reach out Admin team.";
	public static final String USER_NOT_REGISTERED = "User name: %s is not available in our database. Please signup and try again.";
	public static final String USER_NAME_PASSWORD_INVALID = "User name/password invalid try with valid credentials.";

	public static final String FAILED_TO_GET_DB_CONNECTION = "Application Failed to process the request due to technical issues, Please try afte some time.";

	// Constant Fields:

	public static final String PENDING_REQUESTS = "pendingRequests";
	public static final String ACTION = "action";
	public static final String REQUEST_ID = "requestId";
	public static final String APPROVE = "approve";
	public static final String SOFTWARE_LIST = "softwareList";
	public static final String USER_COUNT = "user_count";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String USER_NAME = "username";
	public static final String SOFTWARE_NAME = "softwareName";
	public static final String ACCESS_TYPE_COLUMN = "access_type";
	public static final String STATUS = "status";
	public static final String REASON = "reason";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String USER_ID = "userId";
	public static final String SOFTWARE_ID = "softwareId";
	public static final String ACCESS_TYPE = "accessType";

	// Status Values
	public static final String APPROVED = "Approved";
	public static final String REJECTED = "Rejected";

	// USER ACCESS ROLES
	public static final String EMPLOYEE_ROLE = "Employee";
	public static final String MANAGER_ROLE = "Manager";
	public static final String ADMIN_ROLE = "Admin";

}

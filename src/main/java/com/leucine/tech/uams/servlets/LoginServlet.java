package com.leucine.tech.uams.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.dao.RequestDao;
import com.leucine.tech.uams.dao.SoftwareDao;
import com.leucine.tech.uams.dao.UserDao;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4502326618101809715L;

	private final SoftwareDao softwareDao;
	private final RequestDao requestDao;
	private final UserDao userDao;

	public LoginServlet() {
		this.softwareDao = new SoftwareDao();
		this.requestDao = new RequestDao();
		this.userDao = new UserDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String role = userDao.getAccessRole(request);
			if (role != null) {
				switch (role) {
				case AppConstants.EMPLOYEE_ROLE:
					setSoftwareList(request, response);
					break;
				case AppConstants.MANAGER_ROLE:
					setApprovalData(request, response);
					break;
				case AppConstants.ADMIN_ROLE:
					RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.CREATE_SOFTWARE_JSP_PAGE);
					dispatcher.forward(request, response);
					break;
				}
			} else {
				request.setAttribute(AppConstants.ERROR_MESSAGE, String.format(AppConstants.USER_NOT_REGISTERED, request.getParameter(AppConstants.USER_NAME)));
				RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.LOGIN_JSP_PAGE);
				dispatcher.forward(request, response);
			}

		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.LOGIN_JSP_PAGE);
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(AppConstants.LOGIN_JSP_PAGE_URI).forward(request, response);
	}

	public void setSoftwareList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute(AppConstants.SOFTWARE_LIST, softwareDao.getSoftwareList());
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.REQUEST_ACCESS_JSP_PAGE);
		dispatcher.forward(request, response);
	}

	public void setApprovalData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute(AppConstants.PENDING_REQUESTS, requestDao.fetchPendingRequests());
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.PENDING_REQUEST_JSP_PAGE);
		dispatcher.forward(request, response);
	}
}

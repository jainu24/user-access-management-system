package com.leucine.tech.uams.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.dao.UserDao;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;
import com.leucine.tech.uams.utils.AppUtils;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -509064087809160877L;
	
	private final UserDao  userDao;
	
	public SignUpServlet() {
		this.userDao = new UserDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter(AppConstants.USER_NAME);
		String password = request.getParameter(AppConstants.PASSWORD);
		String role = AppUtils.getRoles().contains(request.getParameter(AppConstants.ROLE))
				? request.getParameter(AppConstants.ROLE)
				: AppConstants.EMPLOYEE_ROLE;
		try {
			userDao.registerNewUser(username, password, role);
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		request.setAttribute(AppConstants.SUCCESS_MESSAGE,
				String.format(AppConstants.USER_REGISTRATION_COMPLETED, username));
		RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.LOGIN_JSP_PAGE);
		dispatcher.forward(request, response);

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(AppConstants.SIGNUP_JSP_PAGE_URI).forward(request, response);
	}
}

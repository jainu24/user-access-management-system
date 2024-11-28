package com.leucine.tech.uams.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.dao.SoftwareDao;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = 6124815267029793677L;

	private final SoftwareDao softwareDao;

	public RequestServlet() {
		this.softwareDao = new SoftwareDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute(AppConstants.USER_ID);
		if (userId == null) {
			response.sendRedirect(AppConstants.LOGIN_JSP_PAGE);
			return;
		}
		String softwareId = request.getParameter("softwareId");
		String accessType = request.getParameter("accessType");
		String reason = request.getParameter("reason");

		try {
			softwareDao.createSoftwareRequest(userId, softwareId, accessType, reason);
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}

		try {
			request.setAttribute(AppConstants.SOFTWARE_LIST, softwareDao.getSoftwareList());
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		request.setAttribute(AppConstants.SUCCESS_MESSAGE, AppConstants.REQUEST_APPROV_SUCCESSFULLY);
		RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.REQUEST_ACCESS_JSP_PAGE);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute(AppConstants.SOFTWARE_LIST, softwareDao.getSoftwareList());
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		request.getRequestDispatcher(AppConstants.REQUEST_ACCESS_JSP_PAGE).forward(request, response);
	}

}

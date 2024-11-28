package com.leucine.tech.uams.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leucine.tech.uams.constants.AppConstants;
import com.leucine.tech.uams.dao.RequestDao;
import com.leucine.tech.uams.exception.UserAccessManagementSystemException;
import com.leucine.tech.uams.models.AccessRequest;

@WebServlet("/approveRequest")
public class ApprovalServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private RequestDao requestDao;
	/**
	 * Initializing he Request DAO class Object in this constructor. It executes at the time of  ApprovalServlet Object creation.
	 */
	public ApprovalServlet(){
		requestDao = new RequestDao();
	}
	
	/**
	 * This method getting called at the time of Request creation page load .
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<AccessRequest> pendingRequestsList = requestDao.fetchPendingRequests();
			request.setAttribute(AppConstants.PENDING_REQUESTS, pendingRequestsList);
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.PENDING_REQUEST_JSP_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * This method used to called at the time of Request approval/Reject process 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String requestId = request.getParameter(AppConstants.REQUEST_ID);
			String status = request.getParameter(AppConstants.ACTION).equalsIgnoreCase(AppConstants.APPROVE)
					? AppConstants.APPROVED
					: AppConstants.REJECTED;
			// Updating the Approve and Reject Status here based on the request Id
			requestDao.updateRequestStatus(requestId, status);

			try {
			// Fetching the Pending Request and adding in to Request Object
			request.setAttribute(AppConstants.PENDING_REQUESTS, requestDao.fetchPendingRequests());
			request.setAttribute(AppConstants.SUCCESS_MESSAGE,String.format(AppConstants.UPDATE_APPROV_REJECT_STATUS, status));
			}catch(UserAccessManagementSystemException e) {
				request.setAttribute(AppConstants.SUCCESS_MESSAGE,String.format(AppConstants.UPDATE_APPROV_REJECT_STATUS, status));
			}
		} catch (UserAccessManagementSystemException e) {
			request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
		}
		// Forward to the JSP with the updated list
		RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.PENDING_REQUEST_JSP_PAGE);
		dispatcher.forward(request, response);

	}

	
}

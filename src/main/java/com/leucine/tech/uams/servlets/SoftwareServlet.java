package com.leucine.tech.uams.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leucine.tech.uams.utils.DatabaseConnection;

@WebServlet("/createSoftware")
public class SoftwareServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489642736218009204L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("softwareName");
		String description = request.getParameter("description");
        String[] accessLevelsArray = request.getParameterValues("accessLevels");
        String accessLevels = (accessLevelsArray != null) ? String.join(",", accessLevelsArray) : "Read";


		try (Connection conn = DatabaseConnection.getConnection()) {
			String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, accessLevels);
			stmt.executeUpdate();
			response.sendRedirect("createSoftware.jsp?createSoftware=success");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Software Creation Failed");
		}
	}
	
}

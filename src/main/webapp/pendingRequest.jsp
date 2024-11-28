<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Requests</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 0;
}

.header {
	background-color: #007bff;
	color: white;
	padding: 20px;
	text-align: center;
}

.container {
	margin: 50px auto;
	padding: 20px;
	max-width: 800px;
	background-color: white;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #007bff;
	color: white;
}

.action-buttons button {
	background-color: #28a745;
	color: white;
	padding: 5px 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.action-buttons button.reject {
	background-color: #dc3545;
}

.action-buttons button:hover {
	opacity: 0.9;
}

.success {
	color: green;
	text-align: center;
	margin-bottom: 20px;
}

.error {
	color: red;
	text-align: center;
	margin-bottom: 20px;
}

.no-requests {
	text-align: center;
	font-size: 18px;
	color: #555;
	margin-top: 20px;
}

.home-button {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
	text-decoration: none;
	text-align: center;
}
.nav {
    margin: 20px auto;
    text-align: center;
}

.nav a {
    margin: 0 10px;
    padding: 10px 20px;
    text-decoration: none;
    color: white;
    background-color: #007bff;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.nav a:hover {
    background-color: #0056b3;
}

.home-button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="header">
		<h1>Pending Requests</h1>
	</div>

 <!-- Navigation Section -->
    <div class="nav">
        <!-- Check if user role is Admin -->
        <c:if test="${sessionScope.role == 'Admin'}">
            <a href="approveRequest">Approve Requests</a>
            <a href="requestAccess">Create New Request</a>
        </c:if>
        <a href="index.jsp">Home</a>
    </div>
    
	<div class="container">
		<!-- Display Success or Error Messages -->
		<c:if test="${not empty errorMessage}">
			<p class="error">${errorMessage}</p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p class="success">${successMessage}</p>
		</c:if>
		<!-- Render table if there are pending requests -->
		<c:if test="${not empty pendingRequests}">
			<table>
				<thead>
					<tr>
						<th>Employee</th>
						<th>Software</th>
						<th>Access Type</th>
						<th>Reason</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="request" items="${pendingRequests}">
						<tr>
							<td>${request.username}</td>
							<td>${request.softwareName}</td>
							<td>${request.accessType}</td>
							<td>${request.reason}</td>
							<td class="action-buttons">
								<form action="approveRequest" method="POST"
									style="display: inline;">
									<input type="hidden" name="requestId" value="${request.id}">
									<input type="hidden" name="action" value="approve">
									<button type="submit">Approve</button>
								</form>
								<form action="approveRequest" method="POST"
									style="display: inline;">
									<input type="hidden" name="requestId" value="${request.id}">
									<input type="hidden" name="action" value="reject">
									<button type="submit" class="reject">Reject</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>

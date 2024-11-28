<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 0;
	position: relative;
	height: 100vh;
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
	max-width: 400px;
	background-color: white;
	border: 1px solid #ccc;
	border-radius: 8px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input, button {
	width: 100%;
	padding: 10px;
	font-size: 16px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button {
	background-color: #28a745;
	color: white;
	cursor: pointer;
}

button:hover {
	background-color: #218838;
}

.success {
	color: green;
	font-weight: bold;
	text-align: center;
	margin-bottom: 15px;
}

.error {
	color: red;
	font-weight: bold;
	text-align: center;
	margin-bottom: 15px;
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

	<!-- Header Section -->
	<div class="header">
		<h1>Login</h1>
		<p>Access your account</p>
	</div>
	<!-- Navigation Section -->
	<div class="nav">
		<a href="index.jsp">Home</a>
	</div>
	<!-- Login Form Section -->
	<div class="container">

		<!-- Display Success or Error Messages -->
		<c:if test="${not empty successMessage}">
			<p class="success">${successMessage}</p>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<p class="error">${errorMessage}</p>
		</c:if>

		<form action="login" method="POST">
			<div class="form-group">
				<label for="username">User Name:</label> <input type="text"
					id="username" name="username" required>
			</div>

			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					id="password" name="password" required>
			</div>

			<button type="submit">Login</button>
		</form>
	</div>

</body>
</html>

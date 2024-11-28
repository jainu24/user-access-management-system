<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Access Management System</title>
    <link rel="icon" type="image/x-icon" href="award/favicon.ico">
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
            margin: 50px;
            text-align: center;
        }
        .btn {
            padding: 15px 30px;
            font-size: 25px;
            color: white;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;
        }
        .btn:hover {
            background-color: #218838;
        }
        .btn-login {
            background-color: #007bff;
        }
        .btn-signup {
            background-color: #ff7f50;
        }
        .btn-login:hover {
            background-color: #0056b3;
        }
        .btn-signup:hover {
            background-color: #e07b3c;
        }
    </style>
</head>
<body>

    <!-- Header Section -->
    <div class="header">
        <h1>Welcome to User Access Management System</h1>
        <p>Your Gateway to manage software access</p>
    </div>

    <!-- Button Section -->
    <div class="container">
        <h2>Choose an action</h2>
        <!-- Login Button pointing to /login (LoginServlet) -->
        <button class="btn btn-login" onclick="window.location.href='login'">Login</button>
        <!-- Sign Up Button pointing to /signup (SignUpServlet) -->
        <button class="btn btn-signup" onclick="window.location.href='signup'">Sign Up</button>
    </div>
</body>
</html>

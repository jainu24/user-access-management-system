User Access Management System
=============================

Overview
--------
This is a web-based User Access Management System that allows users to manage software access requests, user roles, and software details.
It includes role-based functionality for Employees, Managers, and Admins.

Features
--------
1. **Sign Up & Login**:
   - Users can sign up and log in to access the system.
   - Passwords are securely hashed before storing them in the database.

2. **Role-Based Access**:
   - Admins: Can create new users, manage requests, and add software details.
   - Managers: Can approve or reject software access requests.
   - Employees: Can submit requests for software access.

3. **Software Management**:
   - Admins can add new software to the system with access levels (Read, Write, Admin).

4. **Access Requests**:
   - Employees can submit requests for specific software access with reasons.
   - Managers can approve or reject pending requests.

5. **Session Management**:
   - Role-based session handling for enhanced security.

Technologies Used
-----------------
- **Backend**: Java Servlets
- **Frontend**: JSP, HTML, CSS, JSTL
- **Database**: PostgreSQL
- **Server**: Apache Tomcat 9
- **Build Tool**: Maven

Setup Instructions
------------------
1. **Database Setup**:
   - Install PostgreSQL and create a database named `uams_db`.
   - Run the provided SQL scripts to create the required tables:
     - `users`
     - `software`
     - `requests`

2. **Configure Database Connection**:
   - Update the database connection details in `DatabaseConnection.java`:
     ```java
     private static final String DB_URL = "jdbc:postgresql://localhost:5432/uams_db";
     private static final String DB_USER = "your_db_user";
     private static final String DB_PASSWORD = "your_db_password";
     ```

3. **Build and Deploy**:
   - Use Maven to build the project:
     ```
     mvn clean install
     ```
   - Deploy the generated WAR file to the Tomcat server.

4. **Run the Application**:
   - Start the Tomcat server and access the application at:
     ```
     http://localhost:8080/UserAccessManagement
     ```

Folder Structure
----------------
- **src/main/java**: Contains the Java classes for servlets, utilities, and models.
- **src/main/webapp**: Contains the JSP files and static assets (CSS).
- **pom.xml**: Maven configuration file.

Key Pages
---------
- **index.jsp**: Home page.
- **login.jsp**: User login page.
- **signup.jsp**: User registration page.
- **requestAccess.jsp**: Page for employees to request software access.
- **pendingRequest.jsp**: Page for managers to manage access requests.
- **createSoftware.jsp**: Page for admins to add software.

Role-Based Navigation
---------------------
- Admins:
  - Manage requests
  - Create software
  - Create new users
- Managers:
  - View and approve/reject requests
- Employees:
  - Submit requests for software access

Known Issues
------------
- Ensure the database connection details are correct before deployment.
- Sessions must be properly invalidated during logout.

Contact
-------
For issues or support, contact: 
Mail: jainulabedeen2023@gmail.com 
Mobile: 8870396974
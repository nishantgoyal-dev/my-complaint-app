<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp?error=unauthorized");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
</head>
<body>
    <header>
        <h1>Welcome, ${sessionScope.user.username}!</h1>
        <p>Role: ${sessionScope.user.role}</p>
        <nav>
            <a href="raise_complaint.jsp">Raise New Complaint</a> | 
            <a href="${pageContext.request.contextPath}/raise_complaint">My Complaints</a> | 
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </nav>
    </header>
</body>
</html>
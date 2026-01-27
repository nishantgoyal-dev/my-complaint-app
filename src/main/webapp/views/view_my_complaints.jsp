<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
    <title>view_my complaints</title>
</head>
<body>
    <h2>All complaints</h2><br>
    <table border="1">
        <tr>
            <th>Subject</th>
            <th>status</th>
            <th>date</th>
        </tr>
        <c:forEach var="comp" items="${complaintList}">
        <tr>
            <td>${comp.title}</td>
            <td>${comp.status}</td>
            <td>${comp.createdAt}</td>
        </tr>
    </c:forEach>
        
    </table>
    
</body>
</html>
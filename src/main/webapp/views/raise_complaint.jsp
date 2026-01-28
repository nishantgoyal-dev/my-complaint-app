<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("views/login.jsp?error=unauthorized");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Raise Complaint</title>
</head>
<body>
    <h2>Raise complaint</h2>
    <form action="/my-complaint-app/raise_complaint" method="post">
        Enter subject : <input type="text" name = "title"><br>
        Enter discription : <textarea name="description"  cols="30" rows="10">Describe your problem</textarea><br>
        <button type="submit">SUBMIT</button>
    </form>
</body>
</html>
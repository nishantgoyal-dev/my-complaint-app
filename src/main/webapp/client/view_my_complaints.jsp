<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>view_my complaints</title>
        </head>

        <body>
            <h2>All complaints</h2><br>
<table>
    <thead>
        <tr>
            <th>Subject</th>
            <th>Status</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="comp" items="${complaints}">
            <tr>
                <td>${comp.title}</td>
                <td><span class="status-${comp.status}">${comp.status}</span></td>
                <td>${comp.createdAt}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/client/view_details?id=${comp.id}" class="btn">View</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}">Previous</a>
    </c:if>
    <span>Page ${currentPage} of ${totalPages}</span>
    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}">Next</a>
    </c:if>
</div>
        </body>

        </html>
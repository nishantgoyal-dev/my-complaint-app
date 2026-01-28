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
            <table border="1">
                <tr>
                    <th>Subject</th>
                    <th>status</th>
                    <th>date</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="comp" items="${complaints}">
                    <tr>
                        <td>${comp.title}</td>
                        <td>${comp.status}</td>
                        <td>${comp.createdAt}</td>
                        <td>
                            <c:choose>

                                <c:when test="${comp.status == 'PENDING'}">
                                    <a href="${pageContext.request.contextPath}/delete_complaint?id=${comp.id}"
                                        onclick="return confirm('Are you sure you want to withdraw this?')">
                                        Withdraw
                                    </a>
                                </c:when>


                                <c:otherwise>
                                    <span style="color: gray; font-style: italic;">No actions
                                        available</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

            </table>

        </body>

        </html>
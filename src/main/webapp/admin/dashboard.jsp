<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Admin Control Center</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>
        <header>
            <h1>System Administration</h1>
            <div class="stats-bar">
                <span>Total: ${totalCount}</span> |
                <span>Pending: ${pendingCount}</span>
            </div>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </header>

        <main>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Client</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comp" items="${complaints}">
                        <tr>
                            <td>${comp.id}</td>
                            <td>${comp.user.username}</td>
                            <td>${comp.title}</td>
                            <td><span class="status-${comp.status}">${comp.status}</span></td>
                            <td>${comp.createdAt}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/view_complaint?id=${comp.id}"
                                    class="view-btn">
                                    View & Process
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}" class="btn">Previous</a>
                </c:if>

                <span class="page-info">Page ${currentPage} of ${totalPages}</span>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}" class="btn">Next</a>
                </c:if>
            </div>
        </main>
    </body>

    </html>
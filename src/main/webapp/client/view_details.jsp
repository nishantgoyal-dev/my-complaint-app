<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h2>Complaint #${complaint.id}</h2>
        <div class="detail-card">
            <h3>${complaint.title}</h3>
            <p><strong>Status:</strong> <span class="status-${complaint.status}">${complaint.status}</span></p>
            <p><strong>Submitted on:</strong> ${complaint.createdAt}</p>
            <hr>
            <p><strong>Your Description:</strong></p>
            <div class="content">${complaint.description}</div>
            
            <c:if test="${not empty complaint.adminRemarks}">
                <div class="remarks-section">
                    <h4>Admin Feedback</h4>
                    <p>${complaint.adminRemarks}</p>
                </div>
            </c:if>
        </div>

        <div class="actions">
            <c:if test="${complaint.status == 'PENDING'}">
                <a href="${pageContext.request.contextPath}/delete_complaint?id=${complaint.id}" 
                   class="btn-withdraw" onclick="return confirm('Are you sure?')">Withdraw</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/raise_complaint" class="btn-back">Back to My List</a>
        </div>
    </div>
</body>
</html>
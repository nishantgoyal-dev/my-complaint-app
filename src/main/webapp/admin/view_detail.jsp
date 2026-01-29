<div class="complaint-card">
    <h2>Complaint #${complaint.id}</h2>
    <p><strong>From:</strong> ${complaint.user.username}</p>
    <p><strong>Title:</strong> ${complaint.title}</p>
    <hr>
    <p><strong>Description:</strong></p>
    <div class="description-box">${complaint.description}</div>
    
    <form action="${pageContext.request.contextPath}/admin/update_status" method="POST">
        <input type="hidden" name="complaintId" value="${complaint.id}">
        
        <label>Update Status:</label>
        <select name="newStatus">
    <option value="PENDING" ${complaint.status.name() == 'PENDING' ? 'selected' : ''}>Pending</option>
    <option value="IN_PROGRESS" ${complaint.status.name() == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
    <option value="RESOLVED" ${complaint.status.name() == 'RESOLVED' ? 'selected' : ''}>Resolved</option>
    <option value="REJECTED" ${complaint.status.name() == 'REJECTED' ? 'selected' : ''}>Rejected</option>
</select>
        
        <button type="submit">Save Changes</button>
    </form>
    <a href="admin_dashboard">Back to Dashboard</a>
</div>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration form</title>
</head>

<body>
    <h2>Create account</h2><br>
<form action="${pageContext.request.contextPath}/register" method="POST">
    <input type="text" name="username" placeholder="Choose Username" required>
    <input type="password" name="password" placeholder="Create Password" required>
    <button type="submit">Register as Citizen</button>
</form>
    <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/login.jsp">Login here</a></p>
</body>

</html>
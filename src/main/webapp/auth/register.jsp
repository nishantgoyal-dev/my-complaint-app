<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration form</title>
</head>

<body>
    <h2>Create account</h2><br>
<form action="/my-complaint-app/register" method="post">
        <label for="username">username</label>
        <input type="text" name="username" id="username" required>


        <br>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>


        <br>
        <label>Role</label>
        <select name="role">
            <option value="CLIENT">CLIENT</option>
            <option value="ADMIN">ADMIN</option>
        </select>

        <br>
        <button type="submit">Register</button>


    </form>
    <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/login.jsp">Login here</a></p>
</body>

</html>
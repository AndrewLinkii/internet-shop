<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <h1>Login</h1>
    <p>Please fill in this form to enter.</p>
    <hr>

    <input type="text" placeholder="Login" name="login" required>
    <hr>

    <input type="password" placeholder="Enter Password" name="psw" required>
    <hr>

    <hr>
    <h3 style="color: red"> ${msg}</h3>
    <button type="submit">Register</button>
</form>
</body>
</html>

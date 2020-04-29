<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Let's create a new User!

<form action="${pageContext.request.contextPath}/registration" method="post">
    <h1>Registration</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <input type="text" placeholder="Enter Login" name="login" required>
    <hr>

    <input type="text" placeholder="Enter Your Name" name="user_name" required>
    <hr>

    <input type="password" placeholder="Enter Password" name="psw" required>
    <hr>

    <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
    <hr>
    <h3 style="color: red"> ${msg}</h3>
    <button type="submit">Register</button>
</form>
</body>
</html>

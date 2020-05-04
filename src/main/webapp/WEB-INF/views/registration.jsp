<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>

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
    <button type="submit" class="btn btn-primary">Register</button>
</form>
</body>
</html>

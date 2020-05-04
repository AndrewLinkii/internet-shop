<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <h1>Login</h1>
    <p>Please fill in this form to enter.</p>
    <hr>

    <input type="text" placeholder="Login" name="login" required>
    <hr>
    </div>

    <input type="password" placeholder="Enter Password" name="psw" required>
    <hr>

    <hr>
    <h3 style="color: red"> ${msg}</h3>
    <button type="submit">Login</button>
</form>

<form action="${pageContext.request.contextPath}/registration">
    <button type="submit">Registration</button>
</form>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="/WEB-INF/views/css/registrLogin.css"%>
    </style>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <h1>Registration</h1>
            <div>
                <input type="text" placeholder="Login" required="" id="login"  name="login"/>
            </div>
            <div>
                <input type="text" placeholder="Name" required="" id="name" name="user_name"/>
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password"  name="psw"/>
            </div>
            <div>
                <input type="password" placeholder="Password for ADMIN"  id="passwordAdmin"  name="adminPsw"/>
            </div>
            <div>
                <input type="submit" value="Register" />
            </div>
        </form>
        <div>
            <form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Login" />
            </form>
            <h3 style="color: red"> ${msg}</h3>
        </div>
    </section>
</div>
</body>

<%--
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <style>
        body {
            background: url("https://cdn.pixabay.com/photo/2015/05/16/14/45/wall-769963_960_720.jpg") no-repeat;
            background-size: cover;
        }
    </style>
</head>
<center>
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
</center>
</html>
--%>

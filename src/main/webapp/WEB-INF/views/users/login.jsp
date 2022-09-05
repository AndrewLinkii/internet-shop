<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="/WEB-INF/views/css/registrLogin.css"%>
    </style>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <h1>Login Form</h1>
            <div>
                <input type="text" placeholder="Username" required="" id="username" name="login"/>
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="Log in"/>
                <h3 style="color: red"> ${msg}</h3>
            </div>
        </form>
        <div>
            <form action="${pageContext.request.contextPath}/registration" method="get">
                <input type="submit" value="Register"/>
            </form>
        </div>

    </section>
</div>
</body>
</html>

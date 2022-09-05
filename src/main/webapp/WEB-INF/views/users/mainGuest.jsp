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
        <h1>Hello guest</h1>
        <div class="text-center">
            <form action="${pageContext.request.contextPath}/registration" method="get">
                <input type="submit" value="Registration"/>
            </form>
        </div>
        <div class="text-center">
            <form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Login"/>
            </form>
        </div>
    </section>
</div>

</body>
</html>

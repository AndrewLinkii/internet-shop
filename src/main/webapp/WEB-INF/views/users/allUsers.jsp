<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
    </style>
</head>
<body>
<center>
<h3>All Users</h3>
<table border="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th> </th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}"/>
            </td>
            <td>
                <c:out value="${user.name}"/>
            </td>
            <td>
                <c:out value="${user.login}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}"
                   class="btn btn-secondary btn-sm active"  aria-pressed="true">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
    <p></p>
    <form action="${pageContext.request.contextPath}/main">
        <button type="submit" class="btn btn-primary">Menu</button>
    </form>
</center>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Orders</title>
</head>
<body>
<h3>All Orders</h3>
<table border="1">
    <tr>
        <th>Name</th>
    </tr>
    <c:forEach var="order" items="${orders}">
    <tr>
        <td>
            Order : <c:out value="${order.id}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/getOrder?id=${order.id}">show</a>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/deleteOrder?id=${order.id}">delete</a>
        </td>
    </tr>
    </c:forEach>
</body>
</html>

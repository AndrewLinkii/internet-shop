<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
    </style>
</head>
<body>
<center>
    <h3>Hello ${name}</h3>

    <p></p>
    <form action="${pageContext.request.contextPath}/products">
        <button type="submit" class="btn btn-primary">Buy Products</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/shoppingCart">
        <button type="submit" class="btn btn-primary">My bucket</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/orders/user/all">
        <button type="submit" class="btn btn-primary">My orders</button>
    </form>

    <c:if test="${userRole == 'ADMIN'}">
        <jsp:include page="adminPanel.jsp"/>
    </c:if>

    <p></p>
    <form action="${pageContext.request.contextPath}/logout">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form


</center>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllProducts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
    </style>
</head>
<body>
<center>
    <h3>Products</h3>
    <table border="2">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th></th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <c:out value="${product.id}"/>
                </td>
                <td>
                    <c:out value="${product.name}"/>
                </td>
                <td>
                    <c:out value="${product.price}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/shoppingCarts/product/add?id=${product.id}"
                       class="btn btn-secondary btn-sm active" aria-pressed="true">buy</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p></p>
    <form action="${pageContext.request.contextPath}/shoppingCart">
        <button type="submit" class="btn btn-primary">Go to shopping Cart</button>
    </form>
    <p></p>
    <form action="${pageContext.request.contextPath}/main">
        <button type="submit" class="btn btn-primary">Menu</button>
    </form>
</center>
</body>
</html>

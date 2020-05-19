<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shoppingCart</title>
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
<body>
<center>
    <h3>ShoppingCart</h3>
    <table border="1">
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
                    <a href="${pageContext.request.contextPath}/deleteFromCart?id=${product.id}"
                       class="btn btn-secondary btn-sm active" aria-pressed="true">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h4 style="color: red"> ${msg}</h4>
    <p></p>
    <form action="${pageContext.request.contextPath}/completeOrder">
        <button type="submit" class="btn btn-primary">Complete Order</button>
    </form>
    <p></p>
    <form action="${pageContext.request.contextPath}/main">
        <button type="submit" class="btn btn-primary">Menu</button>
    </form>
</center>
</body>
</html>

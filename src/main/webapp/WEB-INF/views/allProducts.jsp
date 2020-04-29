<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllProducts</title>
</head>
<body>
<h3>Products</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
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
                <a href="${pageContext.request.contextPath}/addProductToShopCart?id=${product.id}">add</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p></p>
<form action="${pageContext.request.contextPath}/shoppingCart">
    <button type="submit">Go to shopping Cart</button>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<p></p>
<form action="${pageContext.request.contextPath}/users">
    <button type="submit" class="btn btn-primary">All Users</button>
</form>

<p></p>
<form action="${pageContext.request.contextPath}/orders">
    <button type="submit" class="btn btn-primary">All Orders</button>
</form>

<p></p>
<form action="${pageContext.request.contextPath}/products/edit">
    <button type="submit" class="btn btn-primary">Edit Products</button>
</form>
</body>
</html>

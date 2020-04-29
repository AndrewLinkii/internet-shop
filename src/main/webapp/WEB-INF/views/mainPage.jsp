<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h1>Shop ot Andrew</h1>
    <p></p>
    <form action="${pageContext.request.contextPath}/inject">
        <button type="submit">InjectTestData</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/registration">
        <button type="submit">Registration</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/allUsers">
        <button type="submit">Show Users</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/allProducts">
        <button type="submit">Show Products</button>
    </form>


    <p></p>
    <form action="${pageContext.request.contextPath}/editProducts">
        <button type="submit">Edit Products</button>
    </form>

</head>
<body>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>
<h3>Hello ${name}</h3>


<p></p>
<form action="${pageContext.request.contextPath}/allUsers">
    <button type="submit" class="btn btn-primary">Show Users</button>
</form>

<p></p>
<form action="${pageContext.request.contextPath}/allProducts">
    <button type="submit" class="btn btn-primary">Buy Products</button>
</form>


<p></p>
<form action="${pageContext.request.contextPath}/editProducts">
    <button type="submit" class="btn btn-primary">Edit Products</button>
</form>


<p></p>
<form action="${pageContext.request.contextPath}/allOrders">
    <button type="submit" class="btn btn-primary">All my orders</button>
</form>

</body>
</html>

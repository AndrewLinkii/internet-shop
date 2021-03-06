<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <h3>Hello ${name}</h3>

    <p></p>
    <form action="${pageContext.request.contextPath}/allProducts">
        <button type="submit" class="btn btn-primary">Buy Products</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/shoppingCart">
        <button type="submit" class="btn btn-primary">My bucket</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/allUserOrders">
        <button type="submit" class="btn btn-primary">My orders</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/logout">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>

    <h3> Admins panel </h3>

    <p></p>
    <form action="${pageContext.request.contextPath}/allUsers">
        <button type="submit" class="btn btn-primary">All Users</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/allOrders">
        <button type="submit" class="btn btn-primary">All Orders</button>
    </form>

    <p></p>
    <form action="${pageContext.request.contextPath}/editProducts">
        <button type="submit" class="btn btn-primary">Edit Products</button>
    </form>

</center>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
Let's create a new Product!
<form action="${pageContext.request.contextPath}/addProduct" method="post">

    <p>Please fill in this form to create an product.</p>
    <hr>

    <input type="text" placeholder="Enter name" name="name" required>
    <hr>

    <input type="text" placeholder="Enter price" name="price" required>
    <hr>

    <button type="submit">Add</button>
</form>
</body>
</html>

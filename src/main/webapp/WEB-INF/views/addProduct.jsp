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
<form action="${pageContext.request.contextPath}/addProduct" method="post">

    <h3>Please fill in this form to create an product.</h3>

    <hr>
    <input type="text" placeholder="Enter name" name="name" required>
    <hr>

    <input type="text" placeholder="Enter price" name="price" required>
    <hr>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
</center>
</body>
</html>

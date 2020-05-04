<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
</head>
<body>
<h3>Hello guest</h3>
<p></p>

<p></p>
<form action="${pageContext.request.contextPath}/registration">
    <button type="submit" class="btn btn-primary">Registration</button>
</form>

<p></p>
<form action="${pageContext.request.contextPath}/login">
    <button type="submit" class="btn btn-primary">Login</button>
</form>

</body>
</html>

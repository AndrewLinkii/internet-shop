<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:if test="${userId == null}">
    <jsp:include page="mainGuest.jsp"/>
</c:if>

<c:if test="${userId != null}">
    <jsp:include page="mainUser.jsp"/>
</c:if>

</body>
</html>

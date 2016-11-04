<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>
</head>
<body>
    <c:choose>
        <c:when test="${not empty error}">
            <h1>error</h1>
        </c:when>
        <c:otherwise>
            <h1>${fetchedUser.userName}_${fetchedUser.userOrganizationField}</h1>
        </c:otherwise>
    </c:choose>
</body>
</html>

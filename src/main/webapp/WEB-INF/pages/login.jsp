<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body>
<c:url value="/j_spring_security_check" var="loginUrl" />
<form action="${loginUrl}" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="pes">
    <input type="password" class="form-control" name="j_password" placeholder="Password" required value="pes">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
</form>
</body>
</html>

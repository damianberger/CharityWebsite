<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Change data</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="user-header.jsp" %>

<section class="login-page">
    <h2>Zmień swoje dane osobowe</h2>
    <form:form method="post" modelAttribute="principal" action="/user/user-edit">

        <div class="form-group">
            <form:input path="firstName" placeholder="Imię"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko"/>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="E-mail"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/edit-pass" class="btn btn--without-border">Zmień hasło</a>
            <input class="btn" type="submit" id="changeAndLogout" value="Zatwierdź">
        </div>
    </form:form>
</section>

<%@include file="../footer.jsp" %>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

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
    <title>Change password </title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="user-header.jsp" %>

<section class="login-page">
    <h2>Zmień hasło</h2>
    <form:form action="/user/edit-pass" method="post" modelAttribute="principal">
        <div class="form-group">
            <form:input path="password" value="Hasło" type="password" onfocus="this.value=''"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/user-edit" class="btn btn--without-border">Zmień dane</a>
            <input class="btn" type="submit" id="changeAndLogout" value="Zatwierdź">
        </div>
    </form:form>
</section>


<%@include file="../footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

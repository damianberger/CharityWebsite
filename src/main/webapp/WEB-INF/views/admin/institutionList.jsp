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
<%@include file="admin-header.jsp" %>

<section class="help">

    <div class="help--slides active" data-id="1">
        <ul class="help--slides-items">
            <li>
                <div class="col">
                    <div class="title"><a href="/admin/institution/add"> + Dodaj nową fundację</a></div>
                </div>
            </li>
            <c:forEach items="${institutions}" var="institution">
                <li>
                    <div class="col">
                        <div class="title">Fundacja "${institution.name}"</div>
                        <div class="subtitle"><a href="/admin/institution/edit/${institution.id}">Edytuj  </a><a href="/admin/institution/delete/${institution.id}/">Usuń</a></div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</section>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

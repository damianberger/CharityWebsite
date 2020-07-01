<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="header--login-page">
    <nav class="container container--70">
        <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
            <ul class="nav--actions">
                <li class="logged-user">
                    Witaj  <b> <c:out value="${username}"/> </b>
                    <ul class="dropdown">
                        <li>
                            <a href="#">Profil</a>
                        </li>
                        <li>
                            <a href="#">Moje zbiórki</a>
                        </li>
                        <li>
                            <form action="<c:url value="/logout"/>" method="post">
                                <input class="btn btn--small2 btn--without-border" type="submit" value="Wyloguj">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <ul class="nav--actions">
                <li><a href="login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </ul>
        </sec:authorize>

        <ul>
            <li><a href="/#stats" class="btn btn--without-border active">Start</a></li>
            <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="/#foundations-and-organisations" class="btn btn--without-border">Fundacje i organizacje</a>
            </li>
            <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

</header>


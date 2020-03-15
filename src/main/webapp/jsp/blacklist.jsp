<%@ page import="ru.itis.web_project.logic.permissions.PermissionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Черный список блюд</title>
    <link rel="stylesheet" href="/css/menu.css">
</head>

<body>
<c:set var="accessId" value="${sessionScope.user.role}"/>

<%@ include file="header.jsp" %>

<div class="menu">
    <h1>Черный список блюд</h1>
    <c:if test="${blacklist!=null}">
    <form action="/profile/user/blacklist" method="post">
        <button name="deleteAll" value="deleteAll" type="submit"
                class="btn btn-sm btn-outline-success">
            Отчистить весь список
        </button>
    </form>
    <div class="album py-5">
        <div class="container">
            <div class="row">
                <tag:blacklistTag></tag:blacklistTag>
            </div>
        </div>
    </div>
    </c:if>
    <%@include file="BootstrapScripts.jsp" %>
</body>
</html>

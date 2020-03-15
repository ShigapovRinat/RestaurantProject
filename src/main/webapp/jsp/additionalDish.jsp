<%@ page import="ru.itis.web_project.logic.permissions.PermissionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Добавить в заказ</title>
    <link rel="stylesheet" href="/css/menu.css">
</head>

<body>
<c:set var="accessId" value="${sessionScope.user.role}"/>

<%@ include file="header.jsp" %>

<div class="menu">
    <h1>С вашему блюдами часто заказывают:</h1>
    <c:if test="${addingStatus != null}">
    <h6>${addingStatus}</h6>
    </c:if>

    <div class="album py-5">
        <div class="container">
            <div class="row">
                <tag:additionalDishTag></tag:additionalDishTag>
            </div>
        </div>
    </div>
    <a href="/profile/user/blacklist">Изменить черный список</a>
    <form action="/profile/user/basket" method="post">
        <button name="buy" value="buy" type="submit"
                class="btn btn-sm btn-outline-success">
            Заказать
        </button>
    </form>
    <%@include file="BootstrapScripts.jsp" %>
</body>
</html>
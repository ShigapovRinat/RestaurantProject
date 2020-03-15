<%@ page import="ru.itis.web_project.logic.permissions.PermissionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Menu</title>
    <link rel="stylesheet" href="/css/menu.css">
</head>

<body>
<c:set var="accessId" value="${sessionScope.user.role}"/>

<%@ include file="header.jsp" %>

<div class="menu">
    <h1>Меню</h1>

    <c:if test="${addingStatus != null}">
        <div class="alert alert-success status" role="alert">
            <c:out value="${addingStatus}"/>
        </div>
    </c:if>

    <div class="album py-5">
        <div class="container">
            <div class="row">
                <tag:menuTag permissionList="${permissionList}"></tag:menuTag>
            </div>

        </div>
        <% if (PermissionService.haveAccess("addDishToMenu", permissionList)) {%>
        <form method="get" action="/menu/add-dish">
            <button name="addDish" value="addDish" type="submit"
                    class="btn btn-lg btn-success">
                Добавить блюдо
            </button>
        </form>
        <%}%>
    </div>
</div>


<%@include file="BootstrapScripts.jsp" %>
</body>
</html>

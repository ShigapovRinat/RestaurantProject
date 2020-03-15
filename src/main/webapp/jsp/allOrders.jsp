<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>

    <%@include file="BootstrapConnection.jsp" %>

    <title>Basket</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>

<%--
<c:if test="${sessionScope.user.id==2}"></c:if>
--%>
<table class="table basket">
    <thead>
    <tr>
        <th>ID блюда</th>
        <th>ID пользователя</th>
        <th>Адрес</th>
        <th>Название блюда</th>
        <th>В каком количестве</th>
        <th>Дата</th>
    </tr>
    </thead>
    <tbody>

    <c:if test="${all_orders!=null}">
        <c:forEach var="orders" items="${all_orders}">
            <tr>
                <td>id=${orders.id}</td>
                <td>id=${orders.id_user}</td>
                <td>${orders.userAddress}</td>
                <td>${orders.nameDish}</td>
                <td>${orders.count_id_menu}</td>
                <td>${orders.stringDate}</td>
            </tr>
        </c:forEach>
    </c:if>
    <%--    <button type="submit" class="btn btn-sm btn-outline-danger">Удалить</button>
        </td>
        <td><input type="hidden" name="deleted_count_id" value="${order.count_id_menu}"></td>--%>

    </tbody>

</table>

<%@include file="BootstrapScripts.jsp" %>
</body>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html lang="ru">--%>
<%--<head>--%>
<%--<%@include file="BootstrapConnection.jsp" %>--%>

<%--<title>Menu</title>--%>
<%--<link rel="stylesheet" href="/css/profile.css">--%>
<%--</head>--%>

<%--<body>--%>
<%--<%@ include file="header.jsp" %>--%>

<%--<div class="tab-pane fade show orders col-md-12" id="history" role="tabpanel"--%>
<%--aria-labelledby="history-tab">--%>
<%--<table class="table">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>#</th>--%>
<%--<th>ID пользователяя</th>--%>
<%--<th>Название блюда</th>--%>
<%--<th>В каком количестве</th>--%>
<%--<th>Цена за ед.</th>--%>
<%--<th>Дата</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<tr>--%>
<%--<td>1</td>--%>
<%--<td>1</td>--%>
<%--<td>1</td>--%>
<%--<td>1</td>--%>
<%--<td>1</td>--%>
<%--</tr>--%>

<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>

<%--<%@include file="BootstrapScripts.jsp" %>--%>
<%--</body>--%>
<%--</html>--%>

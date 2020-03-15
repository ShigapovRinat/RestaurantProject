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

<table class="table basket">
    <thead>
    <tr>
    </tr>
    <tr>
        <th>#</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Количество</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="i" value="${1}"/>
    <c:forEach var="order" items="${sessionScope.orderDeliveryList}">
        <form action="/profile/user/basket" method="post">
            <tr>

                <td><c:out value="${i}"/></td>
                <td class="name"><c:out value="${order.name_dish}"/></td>

                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.count_id_menu}"/></td>

                <td>
                    <button name="delete" value="delete" type="submit" class="btn btn-sm btn-outline-danger">Удалить</button>
                </td>
                <td><input type="hidden" name="deleted_count_id" value="${order.count_id_menu}"></td>
                <td><input type="hidden" name="deleted_id" value="${order.id_menu}"></td>
            </tr>
        </form>
        <c:set var="i" value="${i+1}"/>
    </c:forEach>

    </tbody>
    <tfoot>
    <tr>
        <%--<form method="post" action="/profile/user/basket">--%>
        <form method="get" action="/profile/user/basket/offer">
            <td></td>
            <td></td>
            <td></td>
            <td>Итого:
                <c:out value="${sessionScope.totalPriceFromBasket}"/></td>
            <td>
                <button <%--name="buyAll" value="buyAll"--%> type="submit" class="btn btn-sm btn-success">Заказать
                </button>
            </td>
        </form>
    </tr>
    </tfoot>

</table>

<c:if test="${buyStatus != null}">
    <div class="alert alert-success basketStatus" role="alert">
        <c:out value="${buyStatus}"/>
    </div>
</c:if>

<%@include file="BootstrapScripts.jsp" %>
</body>
</html>

<%@ page import="ru.itis.web_project.models.User" %>
<%@ page import="ru.itis.web_project.logic.RoleService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Users</title>
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
        <th>id</th>
        <th>Login</th>
        <th>Username</th>
        <th>Phone number</th>
        <th>Address</th>
        <th>Register date</th>
        <th>Role</th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <form method="post" action="/profile/admin/all-users">
        <%--<c:set var="i" value="${1}"/>
        <c:forEach var="user" items="${userList}">--%>
        <%
            List<User> usersList = (List<User>) request.getAttribute("userList");

            int i = 1;
            for (User user : usersList) {
        %>
        <tr>
            <td><%--<c:out value="${i}"/>--%><%=i++%></td>
            <td><%--<c:out value="${i}"/>--%><%=user.getId()%></td>
            <td>
                <%--<c:out value="${user.login}"/>--%>
                <%=user.getLogin()%>
            </td>
            <td><%--<c:out value="${user.name}"/> --%><%=user.getName()%></td>
            <td><%--<c:out value="${user.phone_number}"/>--%><%=user.getPhone_number()%></td>
            <td><%--<c:out value="${user.address}"/>--%><%=user.getAddress()%></td>
            <td><%--<c:out value="${user.date}"/>--%><%=user.getStringDate()%></td>
            <td><%--<c:out value="${user.role}"/>--%><%=RoleService.getRoleNameByID(user.getRole())%></td>
            <td>

            </td>
            <%-- <c:if test="${user.role != 2 && sessionScope.user.id!=user.id}">--%>
            <%if (user.getRole() != 2 && user.getId() != ((User) httpSession.getAttribute("user")).getId()) {%>
            <td>
                <button name="deleteUser" value="delete" type="submit" class="btn btn-sm btn-outline-danger">
                    Удалить
                </button>
            </td>
            <td><input type="hidden" name="deleted_id" value="${user.id}"></td>
            <%}%>
        </tr>
        <%
            }
        %>

    </form>

    </tbody>
    <tfoot>
    <tr>
        <form method="get" action="/profile/admin/create-user">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <button value="add" type="submit" class="btn btn-sm btn-success">Добавить работника
                </button>
            </td>
        </form>
    </tr>
    </tfoot>

</table>

<%@include file="BootstrapScripts.jsp" %>
</body>
</html>

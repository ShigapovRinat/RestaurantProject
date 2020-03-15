<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <%@include file="../BootstrapConnection.jsp" %>

    <title>Role rights</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ include file="../header.jsp" %>

<table class="table basket">
    <thead>
    <tr>
    </tr>
    <tr>
        <th>Роль</th>
        <th>Добавить</th>
        <th>Права</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <form method="post" action="/profile/admin/roles">

            <td>Администартор</td>
            <td>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                    <label class="form-check-label" for="defaultCheck1">
                        Право 1
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck2">
                    <label class="form-check-label" for="defaultCheck2">
                        Право 2
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck3">
                    <label class="form-check-label" for="defaultCheck2">
                        Право 3
                    </label>
                </div>
                <button name="AddRights" value="add" type="submit" class="btn btn-sm btn-success">
                    Добавить
                </button>
            </td>
            <td>Список прав</td>
        </form>
    </tr>
    </tbody>
</table>

<%@include file="../BootstrapScripts.jsp" %>
</body>
</html>
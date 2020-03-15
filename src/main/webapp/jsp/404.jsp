<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>

    <%@include file="BootstrapConnection.jsp" %>

    <title>Not founded</title>
    <link rel="stylesheet" href="/css/profile.css">
</head>

<body>
<div class="notfound">
    <h1 id="404">404 not founded</h1>
    <form action="/main">
        <button type="submit" class="btn btn-success">Вернуться на главную</button>
    </form>

</div>


<%@include file="BootstrapScripts.jsp" %>
</body>
</html>
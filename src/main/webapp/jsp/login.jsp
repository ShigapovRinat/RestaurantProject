<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Login</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<%@ include file="header.jsp" %>

<div class="container">
    <form class="form" method="post" action="/login">


        <h2 class="form-heading">Войти</h2>
        <br>

        <label for="inputEmail" class="sr-only">Почта</label>
        <input name="login" type="email" id="inputEmail" class="form-control" placeholder="user@mail.ru" required=""
               autofocus="">
        <br>

        <label for="inputPassword" class="sr-only">Пароль</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="password"
               required="">

        <div class="checkbox">
            <label>
                <input name="remember" type="checkbox" value="remember-me"> Запомнить меня
            </label>
        </div>
        <button class="btn btn-lg btn-success btn-block" type="submit">Отправить</button>
        <div>
            Первый раз? <a href="/registration" class="link" style="color: aqua">Зарегистрируйся</a>.
        </div>

        <c:if test="${loginStatus != null}">
            <div class="alert alert-danger loginStatus" role="alert">
                <c:out value="${loginStatus}"/>
            </div>
        </c:if>

    </form>
</div> <!-- /container -->

<%@include file="BootstrapScripts.jsp" %>
</body>
</html>
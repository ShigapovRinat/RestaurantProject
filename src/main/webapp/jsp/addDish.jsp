<%@ page import="ru.itis.web_project.logic.MenuService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>
    <title>Add dish</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
    <c:if test="${addingStatus!=null}">
        <c:out value="${addingStatus}"/>
    </c:if>
    <form action="/menu/add-dish" method="post" enctype="multipart/form-data">

        <h2 class="form-signin-heading">Добавление блюда</h2>
        <br>

        <label for="dishName" class="sr-only">Название</label>
        <input name="name_dish" type="text" id="dishName" class="form-control" placeholder="имя" required=""
               autofocus="">
        <br>

        <label for="inputPrice" class="sr-only">Цена</label>
        <input name="price" type="number" id="inputPrice" class="form-control" placeholder="цена" min="1"
               required=""
               autofocus="">
        <br>

        <label for="inputInfo" class="sr-only">Описание</label>
        <input name="composition" type="text" id="inputInfo" class="form-control" placeholder="описание" required=""
               autofocus="">
        <br>

        <div class="form-row align-items-center">
            <div class="col-auto my-1">
                <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Категория</label>
                <select name="category" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                    <option selected>Выбрать</option>
                    <tag:addDish></tag:addDish>
                </select>
            </div>
        </div>

        <label for="inputPhoto" class="sr-only">Фото</label>
        <input name="photo" type="file" id="inputPhoto" class="form-control" placeholder="фото" accept=".png, .jpg, .jpeg" required>
        <br>

        <button class="btn btn-lg btn-success btn-block" type="submit">Отправить</button>
    </form>

</div>


<%@include file="BootstrapScripts.jsp" %>
</body>
</html>
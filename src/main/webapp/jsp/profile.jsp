<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.itis.web_project.logic.permissions.PermissionService" %>

<html lang="ru">
<head>
    <%@include file="BootstrapConnection.jsp" %>

    <title>Profile</title>
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container emp-profile">
    <%--<form method="post">--%>
    <div class="row">
        <div class="col-md-4">
            <div class="profile-img">
                <img src="../image/rest.png" alt=""/>
                <div class="file btn btn-lg btn-success">
                    Изменить фото
                    <input type="file" name="file"/>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="profile-head">
                <h3>
                    Restaurant
                </h3>
                <h6>
                    ресторан интернациональной кухни
                </h6>
                <h6><c:out value="${changePasswordStatus}"/></h6>
                <h6><c:out value="${changeMainInfoStatus}"/></h6>
<%--
                <p class="proile-rating">Ваши бонусы: <span>2000</span></p>
--%>
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                           aria-controls="home" aria-selected="true">Обо мне</a>
                    </li>
                    <% if (PermissionService.haveAccess("checkHistory", permissionList)) {%>
                    <li class="nav-item">
                        <a class="nav-link" id="history-tab" data-toggle="tab" href="#history" role="tab"
                           aria-controls="history" aria-selected="false">История</a>
                    </li>
                    <%}%>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                           aria-controls="profile" aria-selected="false">Редактировать</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="password-tab" data-toggle="tab" href="#password" role="tab"
                           aria-controls="password" aria-selected="false">Изменить пароль</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-2">
            <form method="get" action="/exit">
                <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Выйти"/>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="tab-content profile-tab" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div class="row">
                        <div class="col-md-6">
                            <label>Имя</label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out value="${sessionScope.user.name}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Email</label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out value="${sessionScope.user.login}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Номер телефона</label>
                        </div>
                        <div class="col-md-6">
                            <p><c:out value="${sessionScope.user.phone_number}"/></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Статус</label>
                        </div>
                        <div class="col-md-6">
                            <p>Золотой</p>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <form method="post" action="/profile">
                        <div class="row">
                            <div class="col-md-6 change-form">
                                <label for="inputName" class="sr-only">Имя</label>
                                <input name="editName" type="text" id="inputName" class="form-control"
                                       placeholder="имя"                                                             <%--СМОТРЕТЬ ИМЯ ПАРАМЕТРА ТУТ--%>
                                       required=""
                                       autofocus="" value="<c:out value="${sessionScope.user.name}"/>">
                                <br>

                                <label for="inputNumber" class="sr-only">Номер</label>
                                <input name="editPhoneNumber" type="number" id="inputNumber"
                                       class="form-control"                                                          <%--СМОТРЕТЬ ИМЯ ПАРАМЕТРА ТУТ--%>
                                       placeholder="номер телефона"
                                       required="" autofocus=""
                                       value="<c:out value="${sessionScope.user.phone_number}"/>">
                                <br>

                                <label for="inputEmail" class="sr-only">Почта</label>
                                <input name="editLogin" type="email" id="inputEmail"
                                       class="form-control"                                                          <%--СМОТРЕТЬ ИМЯ ПАРАМЕТРА ТУТ--%>
                                       placeholder="email" required=""
                                       autofocus="" value="<c:out value="${sessionScope.user.login}"/>">
                                <br>

                                <label for="inputAddress" class="sr-only">Почта</label>
                                <input name="editAddress" type="text" id="inputAddress"
                                       class="form-control"                                                          <%--СМОТРЕТЬ ИМЯ ПАРАМЕТРА ТУТ--%>
                                       placeholder="address" required=""
                                       autofocus="" value="<c:out value="${sessionScope.user.address}"/>">
                                <br>
                            </div>
                            <div class="col-md-4 change-btn">
                                <button class="btn btn-lg btn-outline-success btn-block" type="submit">Изменить</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade show" id="password" role="tabpanel" aria-labelledby="password-tab">
                    <form method="post" action="/profile">
                        <div class="row">
                            <div class="col-md-6 change-form">
                                <label for="inputOldPassword" class="sr-only">Пароль</label>
                                <input name="oldPassword" type="password" id="inputOldPassword"
                                       class="form-control"                                                             <%--СМОТРЕТЬ ИМЯ ПАРАМЕТРА ТУТ--%>
                                       placeholder="старый пароль"
                                       required="a">
                                <br>
                                <label for="inputNewPassword" class="sr-only">Пароль</label>
                                <input name="newPassword" type="password" id="inputNewPassword"
                                       class="form-control"                                                             <%--СМОТРЕТЬ ИМЯ ПАРАМЕТРА ТУТ--%>
                                       placeholder="новый пароль"
                                       required="a">
                                <br>
                            </div>

                            <div class="col-md-4 change-btn">
                                <button class="btn btn-lg btn-outline-success btn-block" type="submit">Изменить</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade show orders col-md-12" id="history" role="tabpanel"
                     aria-labelledby="history-tab">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Название блюда</th>
                            <th>В каком количестве</th>
                            <th>Цена за ед.</th>
                            <th>Дата</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:set var="i" scope="page" value="${1}"/>
                        <c:forEach var="orders" items="${deliveryList}">
                            <tr>
                                <td>${i}</td>
                                <td>${orders.name_dish}</td>
                                <td>${orders.count_id_menu}</td>
                                <td>${orders.price}</td>
                                <td>${orders.stringDate}</td>
                            </tr>
                            <c:set var="i" scope="page" value="${i+1}"/>
                        </c:forEach>


                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="BootstrapScripts.jsp" %>
</body>
</html>
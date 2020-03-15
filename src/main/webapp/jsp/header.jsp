<%@ page import="java.util.List" %>
<%@ page import="ru.itis.web_project.logic.permissions.PermissionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/main">Restaurant</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/main">Главная <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/menu">Меню</a>
                </li>
                <%--<li class="nav-item">
                    <a class="nav-link" href="#">Галерея</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Акция</a>
                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="/reviews">Отзывы</a>
                </li>

            </ul>
            <ul class="navbar-nav ml-auto">
                <% HttpSession httpSession = request.getSession(false);
                    List<Integer> permissionList = (List<Integer>) httpSession.getAttribute("permissionList");
                    if (permissionList == null) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Зарегестрироваться</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Войти</a>
                </li>
                <%} else {%>

                <li class="nav-justified dropleft justify-content-end">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:out value="${sessionScope.user.name}"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">

                        <a class="dropdown-item" href="/profile">Профиль</a> <%--ALL--%>


                        <%if (PermissionService.haveAccess("checkBasket", permissionList)) {%>
                        <a class="dropdown-item" href="/profile/user/basket">Корзина</a> <%--USER--%>
                        <a class="dropdown-item" href="/profile/user/blacklist">Черный список</a>
                        <%}%>

                        <%if (PermissionService.haveAccess("addDishToMenu", permissionList)) {%>
                        <a class="dropdown-item" href="/menu/add-dish">Администрирование меню</a> <%--ADMIN--%>
                        <%}%>

                        <%if (PermissionService.haveAccess("showUsers", permissionList)) {%>
                        <a class="dropdown-item" href="/profile/admin/all-users">Клиенты/работники</a> <%--ADMIN--%>
                        <%}%>

                        <%if (PermissionService.haveAccess("showOrders", permissionList)) {%>
                        <a class="dropdown-item" href="/profile/all-orders">Посмотреть заказы</a> <%--ADMIN, KITCHEN--%>
                        <%}%>

                        <hr>
                        <a class="dropdown-item" href="/exit">Выйти</a> <%--ALL--%>
                    </div>
                </li>

                <%}%>
            </ul>
        </div>
    </nav>
</header>

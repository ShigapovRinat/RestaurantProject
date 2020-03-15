<%@ tag import="ru.itis.web_project.logic.permissions.PermissionService" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="permissionList" required="true" type="java.util.List" %>

<c:forEach var="dish" items="${menuList}">
    <form method="post" action="/menu">
        <div class="row">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="../../image/${dish.fileName}">
                    <div class="card-body">
                        <p class="card-text">Название: ${dish.name}</p>
                        <p class="card-text">Инфа о блюде: ${dish.composition}</p>
                        <br>
                        <div class="d-flex justify-content-between align-items-center">
                            <small class="text-muted">цена: ${dish.price}</small>
                            <div>

                                <% if (PermissionService.haveAccess("addToBasket", permissionList)) {%>
                                <input name="id_count_menu" type="number" min="1" max="10" step="1"
                                       value="1"
                                       pattern="[0-9]*">
                                <%}%>

                                <input name="id_menu" value="${dish.id}" type="hidden">
                            </div>

                            <br>
                            <div class="btn-group">
                                <% if (PermissionService.haveAccess("addToBasket", permissionList)) {%>
                                <button name="add" value="add" type="submit"
                                        class="btn btn-sm btn-outline-success">
                                    Добавить
                                </button>
                                <%}%>
                                <% if (PermissionService.haveAccess("removeDishFromMenu", permissionList)) {%>
                                <button name="delete" value="delete" type="submit"
                                        class="btn btn-sm btn-outline-danger">
                                    Удалить
                                </button>
                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</c:forEach>
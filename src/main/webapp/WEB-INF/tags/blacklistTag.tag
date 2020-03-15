<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${blacklist}" var="dish">
    <form action="/profile/user/blacklist" method="post">
        <div class="card mb-4 shadow-sm">
            <img src="../../image/<c:out value="${dish.fileName}"/>">
            <div class="card-body">
                <p class="card-text">Название: ${dish.name}</p>
                <p class="card-text">Инфа о блюде: ${dish.composition}</p>
                <br>
                <div class="d-flex justify-content-between align-items-center">
                    <small class="text-muted">цена: ${dish.price}</small>
                    <div>
                        <input name="id_count_menu" type="number" min="1" max="10" step="1"
                               value="1"
                               pattern="[0-9]*">

                        <input name="dish_id" value="${dish.id}" type="hidden">
                        <br>
                    </div>
                </div>
                <button name="delete" value="delete" type="submit"
                        class="btn btn-sm btn-outline-success">
                    Удалить из черного списка
                </button>
            </div>
        </div>
    </form>
</c:forEach>
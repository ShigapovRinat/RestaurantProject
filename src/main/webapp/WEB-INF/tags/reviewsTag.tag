<%@ tag import="ru.itis.web_project.logic.permissions.PermissionService" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="permissionList" required="true" type="java.util.List" %>

<c:forEach items="${requestScope.reviewList}" var="review">
    <tr>
        <td> ${review.username}</td>
        <td> ${review.message}</td>
        <td> ${review.raiting}</td>
        <td>
                ${review.stringDate}
        </td>
        <%
            if (PermissionService.haveAccess("deleteReviews", permissionList)) {
        %>
        <form action="/reviews" method="post">
            <td>
                <button name="deleteReview" value="${review.id}" type="submit" class="btn btn-sm btn-outline-danger">
                    Удалить отзыв
                </button>
            </td>
        </form>
        <%
            }
        %>
    </tr>
</c:forEach>
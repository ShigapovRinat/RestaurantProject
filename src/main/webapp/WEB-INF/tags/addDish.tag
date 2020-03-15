<%@ tag import="ru.itis.web_project.logic.MenuService" %>
<%@ tag import="java.util.List" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<%
    List<String> list = MenuService.getCategoryList();
    for (String str : list) {
%>
<option value="<%=str%>"><%=str%>
</option>
<%
    }
%>
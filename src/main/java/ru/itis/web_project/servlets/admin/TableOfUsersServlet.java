package ru.itis.web_project.servlets.admin;

import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.admin_action.AdminsAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile/admin/all-users")
public class TableOfUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminsAction.deleteUser(request.getParameter("deleted_id"));
        response.sendRedirect("/profile/admin/all-users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usersList = AdminsAction.getAllUsers();
        request.setAttribute("userList",usersList);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }
}

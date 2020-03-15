package ru.itis.web_project.servlets;

import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.delivery_utils.DeliveryTableUtil;
import ru.itis.web_project.logic.EditProfile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String oldPassword = request.getParameter("oldPassword");
        if (oldPassword != null) {
            if (EditProfile.editPassword(request)) {
                request.setAttribute("changePasswordStatus", "Пароль был успешно изменён");
            } else {
                request.setAttribute("changePasswordStatus", "Предыдущий пароль был введён неверно");
            }
        } else {
            EditProfile.editMainInfo(request);
            request.setAttribute("changeMainInfoStatus", "Данные изменены успешно");
        }
        response.sendRedirect("/profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("deliveryList", DeliveryTableUtil.getTableDeliveryOrder(((User) request.getSession().getAttribute("user")).getId()));
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }
}

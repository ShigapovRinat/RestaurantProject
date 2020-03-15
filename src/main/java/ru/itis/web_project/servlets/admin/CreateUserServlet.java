package ru.itis.web_project.servlets.admin;

import ru.itis.web_project.logic.Registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/admin/create-user")
public class CreateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (Registration.registerWorker(request)) {
            request.setAttribute("registrCode", true);
            request.setAttribute("registrStatus", "Работник был зарегестрирован");
        } else {
            request.setAttribute("registrStatus", "Работник не был зарегестрирован");
            request.setAttribute("registrCode", false);
        }
        request.getRequestDispatcher("/jsp/registrationWorker.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registrationWorker.jsp").forward(request, response);
    }
}

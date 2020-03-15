package ru.itis.web_project.servlets.kitchen;

import ru.itis.web_project.logic.AllOrders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/all-orders")
public class AllOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("all_orders",AllOrders.showAllOrders());
        request.getRequestDispatcher("/jsp/allOrders.jsp").forward(request, response);
    }
}

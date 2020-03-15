package ru.itis.web_project.servlets;

import ru.itis.web_project.logic.additionalLayer.DishesServiceLayer;
import ru.itis.web_project.logic.user_action.DishesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/user/basket")

public class BasketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("delete") != null) {
            /*DishesService.deleteDishFromBasket(request);*/
            DishesServiceLayer.deleteDishFromBasket(request);
        } else if (request.getParameter("buy") != null) {
            /*DishesService.buyFromBasket(request);*/
            DishesServiceLayer.buyFromBasket(request);
            request.setAttribute("buyStatus", "Заказ был добавлен");
        }
        request.getRequestDispatcher("/jsp/basket.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/basket.jsp").forward(request, response);
    }
}

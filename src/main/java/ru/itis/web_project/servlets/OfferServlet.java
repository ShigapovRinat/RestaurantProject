package ru.itis.web_project.servlets;

import ru.itis.web_project.logic.additionalLayer.DishesServiceLayer;
import ru.itis.web_project.models.Dish;
import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.DishToOffer;
import ru.itis.web_project.logic.user_action.DishesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/profile/user/basket/offer")
public class OfferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            request.setAttribute("addingStatus", "Блюдо добавлено в ваш заказ");
            /*DishesService.toBasket(request);*/
            DishesServiceLayer.toBasket(request);
            response.sendRedirect("/profile/user/basket");
        } else if (request.getParameter("delete") != null) {
            DishesService.updateUserAntipathy(false,
                    ((HashSet<Integer>) request.getSession(false).getAttribute("userAntipathySet")),
                    Integer.parseInt(request.getParameter("id_menu")),
                    ((User) request.getSession(false).getAttribute("user")).getId());

            doGet(request, response);
            /*((HashSet<Integer>) request.getSession(false).getAttribute("userAntipathySet"))
                    .add(Integer.parseInt(request.getParameter("id_menu")));*/
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("orderDeliveryList") != null) {

            Set<Integer> usersAntipathySet = (HashSet<Integer>) session.getAttribute("userAntipathySet");
            Set<Integer> dishIdFromBasketSet = (HashSet<Integer>) session.getAttribute("dishIdSet");
            List<Dish> list = DishToOffer.makeOffer( usersAntipathySet, dishIdFromBasketSet);
            if (list != null) {
                session.setAttribute("dishListForOffer", list);
                request.getRequestDispatcher("/jsp/additionalDish.jsp").forward(request, response);
            } else {
                /*DishesService.buyFromBasket(request);*/
                DishesServiceLayer.buyFromBasket(request);
                request.setAttribute("buyStatus", "Заказ был добавлен");
                request.getRequestDispatcher("/jsp/basket.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("buyStatus", "Список пуст");
            request.getRequestDispatcher("/jsp/basket.jsp").forward(request, response);
        }

    }
}

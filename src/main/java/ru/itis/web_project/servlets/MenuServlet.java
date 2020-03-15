package ru.itis.web_project.servlets;

import ru.itis.web_project.logic.additionalLayer.DishesServiceLayer;
import ru.itis.web_project.logic.user_action.DishesService;
import ru.itis.web_project.logic.admin_action.EditMenu;
import ru.itis.web_project.logic.MenuService;
import ru.itis.web_project.models.Dish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("add") != null) {
            request.setAttribute("addingStatus", "Блюдо добавлено в ваш заказ");
            /*DishesService.toBasket(request);*/
            DishesServiceLayer.toBasket(request);
        } else if (request.getParameter("delete") != null) {
            EditMenu.deleteFromMenu(Integer.parseInt(request.getParameter("id_menu")));
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        MenuService.getMenuList(request);
*/
        List<Dish> list = MenuService.getMenuList();
        if (list != null)
            request.setAttribute("menuList", list);
        request.getRequestDispatcher("/jsp/menu.jsp").forward(request, response);
    }
}

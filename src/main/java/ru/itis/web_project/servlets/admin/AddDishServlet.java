package ru.itis.web_project.servlets.admin;

import ru.itis.web_project.logic.admin_action.EditMenu;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menu/add-dish")
@MultipartConfig
public class AddDishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (EditMenu.addToMenu(request.getPart("photo"),
                request.getParameter("name_dish"),
                Integer.parseInt(request.getParameter("price")),
                request.getParameter("composition"),
                request.getParameter("category"))) {

            request.setAttribute("addingStatus", "Блюдо добавлено в меню");
        } else {
            request.setAttribute("addingStatus", "Блюдо не было добавлено в меню");
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addDish.jsp").forward(request, response);
    }
}

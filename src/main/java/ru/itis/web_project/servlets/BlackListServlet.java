package ru.itis.web_project.servlets;

import ru.itis.web_project.logic.BlackList;
import ru.itis.web_project.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/profile/user/blacklist")
public class BlackListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("deleteAll") != null) {
            request.getSession(false).setAttribute("userAntipathySet", new HashSet<>());
            BlackList.deleteAllFromBlackList(((User) request.getSession(false).getAttribute("user")).getId());
        } else if (request.getParameter("delete") != null) {
            HttpSession httpSession = request.getSession(false);
            Integer user_id = ((User) httpSession.getAttribute("user")).getId();
            Integer dish_id = Integer.parseInt(request.getParameter("dish_id"));
            Set<Integer> antipathy = (HashSet<Integer>) httpSession.getAttribute("userAntipathySet");
            BlackList.deleteFromBlackList(user_id, dish_id, antipathy);
        }
        response.sendRedirect("/profile/user/blacklist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("blacklist", BlackList.getList(((User) request.getSession(false).getAttribute("user")).getId()));
        request.getRequestDispatcher("/jsp/blacklist.jsp").forward(request, response);
    }
}

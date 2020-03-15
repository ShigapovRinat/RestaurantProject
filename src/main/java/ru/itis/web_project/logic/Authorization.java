package ru.itis.web_project.logic;

import ru.itis.web_project.logic.additionalLayer.AuthorizationLayer;
import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.permissions.PermissionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class Authorization {

    public static boolean isItCorrect(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> user = AuthorizationLayer.getUserByLoginAndPassword(request.getParameter("login"), (request.getParameter("password")));

        if (!user.isPresent()) {
            return false;
        } else {
            setAttributesForSession(request, user);
            if (request.getParameter("remember") != null) {
                Cookie cookie = new Cookie("userId", String.valueOf(user.get().getId()));
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            }
            return true;
        }
    }

    public static boolean giveAccess(Integer userId, HttpServletRequest request) {
        Optional<User> user = AuthorizationLayer.getUserById(userId);
        if (!user.isPresent()) {
            return false;
        } else {
            setAttributesForSession(request, user);
            return true;
        }
    }

    private static void setAttributesForSession(HttpServletRequest request, Optional<User> user) {
        User user1 = user.get();
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user1);
        session.setAttribute("userAntipathySet", AuthorizationLayer.getUserAntipathy(user1.getId()));
        session.setAttribute("permissionList", PermissionService.setPermissionForUser(user1.getRole()));
        session.setAttribute("totalPriceFromBasket", 0);
    }
}

package ru.itis.web_project.filters;

import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.Authorization;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile")
public class ProfileFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        boolean UserHasAccess = false;
        if (session == null || session.getAttribute("user") == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userId")) {
                        if (Authorization.giveAccess(Integer.parseInt(cookie.getValue()), request)) {        //нашёл в бд пользователя с таким id
                            UserHasAccess = true;
                        }
                    }
                }
            }
            if (!UserHasAccess) {
                response.sendRedirect("/login");
                return;
            }
        }
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

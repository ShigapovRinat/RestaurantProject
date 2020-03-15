package ru.itis.web_project.filters;

import ru.itis.web_project.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "WaiterAccessFilter")
public class WaiterAccessFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        Integer accessId =  ((User) session.getAttribute("user")).getRole() ;

        if (accessId == 3 || accessId == 1) {
            chain.doFilter(req, resp);
        } else {
            request.getRequestDispatcher("/jsp/404.jsp").forward(request, response);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

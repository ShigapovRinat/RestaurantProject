package ru.itis.web_project.servlets;

import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String delete_id;
        if ((delete_id = request.getParameter("deleteReview")) != null) {
            ReviewService.deleteReview(delete_id);
        } else if (request.getParameter("addReview") != null) {
            ReviewService.addReview(((User)request.getSession(false).getAttribute("user")).getId(), request.getParameter("mark"),request.getParameter("review"));
        }
        response.sendRedirect("/reviews");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String avg = new DecimalFormat("#0.0").format(ReviewService.getAvgRate());
        request.setAttribute("reviewRating", avg);
        request.setAttribute("reviewList", ReviewService.getReviewList());
        request.getRequestDispatcher("/jsp/reviews.jsp").forward(request, response);
    }

}

package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.BookReviewDAO;
import ru.itis.web_project.models.BookReview;

import java.util.List;

public class ReviewService {
    public static List<BookReview> getReviewList() {
        return BookReviewDAO.getAllBookReview().orElse(null);
    }

    public static Double getAvgRate() {
        /*System.out.println(BookReviewDAO.getAvgRating().orElse(-5.0));*/
        return BookReviewDAO.getAvgRating().orElse(0.0);
    }

    public static void deleteReview(String delete_id) {
        BookReviewDAO.deleteReviewById(Integer.parseInt(delete_id));
    }

    public static void addReview(Integer user_id, String rating, String review){
        BookReview bookReview = new BookReview();
        bookReview.setId_user(user_id);
        bookReview.setRaiting(Integer.parseInt(rating));
        bookReview.setMessage(review);
        BookReviewDAO.insertReview(bookReview);
    }
}

package ru.itis.web_project.DAO;

import ru.itis.web_project.models.BookReview;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookReviewDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static void insertReview(BookReview bookReview) {
        String sqlQuery = "INSERT INTO book (id_user,date,message,rating) VALUES (?,NOW(),?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, bookReview.getId_user());
            ps.setString(2, bookReview.getMessage());
            ps.setInt(3, bookReview.getRaiting());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteReviewById(Integer id) {
        String sqlQuery = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Optional<List<BookReview>> getAllBookReview() {
        List<BookReview> list = null;
        String sqlQuery = "SELECT * FROM book ORDER BY date";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    list = new ArrayList<>();
                    list.add(rowMapper.mapRow(rs));
                    while (rs.next()) {
                        list.add(rowMapper.mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(list);
    }

    public static Optional<Double> getAvgRating() {
        Double avgRating = null;
        String sqlQuery = "SELECT AVG(rating) AS avg_rate FROM book ";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    avgRating = resultSet.getDouble("avg_rate");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(avgRating);
    }

    private static RowMapper<BookReview> rowMapper = row -> {
        BookReview bookReview = new BookReview();
        bookReview.setId(row.getInt("id"));
        bookReview.setId_user(row.getInt("id_user"));
        bookReview.setRaiting(row.getInt("rating"));
        bookReview.setMessage(row.getString("message"));
        bookReview.setDate(row.getDate("date"));
        bookReview.setUsername(UserDAO.findUserById(bookReview.getId_user()).get().getName());
        return bookReview;
    };

}

package ru.itis.web_project.DAO;

import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AntipathyDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static Set<Integer> getUsersAntipathy(int userId) {
        Set<Integer> set = new HashSet<>();
        String sqlQuery = "SELECT dish_id FROM antipathy WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, userId);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    set.add(resultSet.getInt("dish_id"));
                    while (resultSet.next()) {
                        set.add(resultSet.getInt("dish_id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    public static void insert(int user_id, int dish_id) {
        String sqlQuery = "INSERT INTO antipathy (user_id, dish_id) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, user_id);
            ps.setInt(2, dish_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int user_id, int dish_id) {
        String sqlQuery = "DELETE FROM antipathy WHERE user_id = ? AND dish_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, user_id);
            ps.setInt(2, dish_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll(int user_id) {
        String sqlQuery = "DELETE FROM antipathy WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, user_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

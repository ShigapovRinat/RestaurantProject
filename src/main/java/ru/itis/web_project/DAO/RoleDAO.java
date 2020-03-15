package ru.itis.web_project.DAO;

import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static String getNameById(Integer id) {
        String sqlQuery = "SELECT name FROM role WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getIdByName(String name) {
        String sqlQuery = "SELECT id FROM role WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static List<String> getRoleNames() {
        String sqlQuery = "SELECT name FROM role";
        List<String> list = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    list = new ArrayList<>();
                    list.add(resultSet.getString("name"));
                }
                while (resultSet.next()) {
                    list.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

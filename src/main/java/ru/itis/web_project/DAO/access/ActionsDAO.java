package ru.itis.web_project.DAO.access;

import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionsDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static String getActionByActionID(Integer action_id) {
        String sqlQuery = "SELECT action FROM actions WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, action_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("action");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getActionNameByActionID(Integer action_id) {
        String sqlQuery = "SELECT action_name FROM actions WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, action_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("action_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getActionIDbyAction(String action) {
        String sqlQuery = "SELECT id FROM actions WHERE action = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, action);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

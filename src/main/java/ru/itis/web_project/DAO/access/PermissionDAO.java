package ru.itis.web_project.DAO.access;

import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PermissionDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static Optional<List<Integer>> getActionsByRoleId(Integer role_id) {
        String sqlQuery = "SELECT id_actions FROM permission WHERE id_role = ?";
        List<Integer> list = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, role_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    list = new ArrayList<>();
                    list.add(rs.getInt("id_actions"));
                }
                while (rs.next()) {
                    list.add(rs.getInt("id_actions"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(list);
    }
}

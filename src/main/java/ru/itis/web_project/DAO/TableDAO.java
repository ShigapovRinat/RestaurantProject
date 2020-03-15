package ru.itis.web_project.DAO;

import ru.itis.web_project.models.Table;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TableDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static Optional<Table> findById(Integer id) {
        String sqlQuery = "SELECT * FROM table WHERE id = ?";
        Table table = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    table = rowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(table);
    }

    public static void updateTable(Table table) {
        String sqlQuery = "UPDATE table SET count_guests =?  WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, table.getCountOfGuests());
            ps.setInt(2, table.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static RowMapper<Table> rowMapper = row -> {
        Table table = new Table();
        table.setId(row.getInt("id"));
        table.setCountOfGuests(row.getInt("count_guests"));
        return table;
    };

}

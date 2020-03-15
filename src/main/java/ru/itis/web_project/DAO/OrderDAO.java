package ru.itis.web_project.DAO;

import ru.itis.web_project.models.Order;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static void deleteAllOrdersByTable(Integer id_table) {
        String sqlQuery = "DELETE FROM order WHERE id_table = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_table);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Optional<List<Order>> getOrderListForCurrentTable(Integer id_table) {
        String sqlQuery = "SELECT * FROM order WHERE id_table = ?";
        ArrayList<Order> orders = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_table);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orders = new ArrayList<>();
                    orders.add(rowMapper.mapRow(rs));
                    while (rs.next()) {
                        orders.add(rowMapper.mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(orders);
    }

    public static void insertNewOrder(Order order) {
        String sqlQuery = "INSERT INTO order (id_table, id_menu, count, date) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, order.getId_table());
            ps.setInt(2, order.getId_menu());
            ps.setInt(3, order.getCount_id_menu());
            ps.setDate(4, order.getDate());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static RowMapper<Order> rowMapper = row -> {
        Order order = new Order();
        order.setId_table(row.getInt("id_table"));
        order.setId_menu(row.getInt("id_menu"));
        order.setCount_id_menu(row.getInt("count"));
        order.setDate(row.getDate("date"));
        return order;
    };

}

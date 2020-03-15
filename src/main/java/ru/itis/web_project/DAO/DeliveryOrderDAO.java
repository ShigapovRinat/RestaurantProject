package ru.itis.web_project.DAO;

import ru.itis.web_project.models.DeliverOrder;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryOrderDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static List<DeliverOrder> showAllDeliverOrders() {
        List<DeliverOrder> list = null;
        String sqlQuery = "SELECT delivery_orders.*, menu.name_dish, user.address FROM delivery_orders LEFT JOIN menu ON delivery_orders.id_menu = menu.id LEFT JOIN user ON delivery_orders.id_user = user.id";

        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    list = new ArrayList<>();
                    list.add(rowMapper.mapRow(rs).setNameDish(rs.getString("name_dish")).setUserAddress(rs.getString("address")));
                    while (rs.next()) {
                        list.add(rowMapper.mapRow(rs).setNameDish(rs.getString("name_dish")).setUserAddress(rs.getString("address")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(list).orElse(null);
    }


    public static Optional<List<DeliverOrder>> getAllDeliveryOrdersByIdUser(Integer id_user) {
        List<DeliverOrder> list = null;
        String sqlQuery = "SELECT * FROM delivery_orders WHERE id_user = ? ORDER BY date";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_user);
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

    public static void insertDeliveryOrder(DeliverOrder deliveryOrder) {
        String sqlQuery = "INSERT INTO delivery_orders (id_user, count_dish, id_menu, date) VALUES (?,?,?, NOW())";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, deliveryOrder.getId_user());
            ps.setInt(2, deliveryOrder.getCount_id_menu());
            ps.setInt(3, deliveryOrder.getId_menu());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static RowMapper<DeliverOrder> rowMapper = row -> {
        DeliverOrder deliveryOrder = new DeliverOrder();
        deliveryOrder.setId(row.getInt("id"));
        deliveryOrder.setId_user(row.getInt("id_user"));
        deliveryOrder.setId_menu(row.getInt("id_menu"));
        deliveryOrder.setCount_id_menu(row.getInt("count_dish"));
        deliveryOrder.setDate(row.getDate("date"));
        return deliveryOrder;
    };
}

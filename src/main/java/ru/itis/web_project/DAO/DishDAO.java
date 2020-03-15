package ru.itis.web_project.DAO;

import ru.itis.web_project.models.Dish;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static boolean insert(Dish dish) {
        String sqlQuery = "INSERT INTO menu (name_dish, price, composition,id_category,filePath) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, dish.getName());
            ps.setInt(2, dish.getPrice());
            ps.setString(3, dish.getComposition());
            ps.setInt(4, dish.getId_category());
            ps.setString(5,dish.getFileName());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Dish findByName(String name) {
        String sqlQuery = "SELECT * FROM menu WHERE name_dish = ? AND isUsed = 1";
        Dish dish = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dish = rowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    public static Optional<List<Dish>> getAllDishes() {
        String sqlQuery = "SELECT * FROM menu WHERE isUsed = 1 ORDER BY id_category";
        List<Dish> list = null;
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


    public static Optional<Dish> getDishById(Integer id) {
        String sqlQuery = "SELECT * FROM menu WHERE id = ?";
        Dish dish = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dish = rowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(dish);
    }

    public static Optional<List<Dish>> getAllDishesByCategory(Integer id_category) {
        String sqlQuery = "SELECT * FROM menu WHERE id_category = ?  AND isUsed = 1";
        List<Dish> list = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_category);
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

    public static void updateDish(Dish dish) {
        String sqlQuery = "UPDATE menu SET name_dish=?, price=?, composition=?, id_category=?  WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, dish.getName());
            ps.setInt(2, dish.getPrice());
            ps.setString(3, dish.getComposition());
            ps.setInt(4, dish.getId_category());
            ps.setInt(5, dish.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDishFromMenu(Integer id) {
        /*String sqlQuery = "DELETE FROM menu WHERE id = ?";*/
        String sqlQuery = "UPDATE menu SET isUsed = 0 WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static RowMapper<Dish> rowMapper = row -> {
        Dish dish = new Dish();
        dish.setId(row.getInt("id"));
        dish.setName(row.getString("name_dish"));
        dish.setPrice(row.getInt("price"));
        dish.setComposition(row.getString("composition"));
        dish.setId_category(row.getInt("id_category"));
        dish.setFileName(row.getString("filePath"));
        return dish;
    };

}

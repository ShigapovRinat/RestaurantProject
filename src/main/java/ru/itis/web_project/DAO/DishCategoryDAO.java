package ru.itis.web_project.DAO;

import ru.itis.web_project.models.DishCategory;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishCategoryDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static void insertCategory(DishCategory dishCategory) {
        String sqlQuery = "INSERT INTO dish_category (categ_name) VALUE ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, dishCategory.getCateg_name());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAllCategories() {
        String sqlQuery = "SELECT categ_name FROM dish_category";
        List<String> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("categ_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Integer getIdCategoryByName(String categ_name) {
        String sqlQuery = "SELECT id FROM dish_category WHERE categ_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, categ_name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

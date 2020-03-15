package ru.itis.web_project.DAO;

import ru.itis.web_project.models.DishPair;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishPairDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();

    public static void insertNewPair(int dish_1, int dish_2) {
        String sqlQuery = "INSERT INTO dish_pair (id_dish_1, id_dish_2) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, dish_1);
            ps.setInt(2, dish_2);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePair(int dish) {
        String sqlQuery = "DELETE FROM dish_pair WHERE id_dish_1=? or id_dish_2=?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, dish);
            ps.setInt(2, dish);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePair(int dish_1, int dish_2) {
        int count = -100;
        String sql = "SELECT count FROM dish_pair WHERE id_dish_1 = ? and id_dish_2 = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, dish_1);
            ps.setInt(2, dish_2);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sqlQuery = "UPDATE dish_pair SET count =? WHERE id_dish_1 = ? and id_dish_2 = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, count + 1);
            ps.setInt(2, dish_1);
            ps.setInt(3, dish_2);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<DishPair> getAllPairs(Integer id_dish) {
        /*List<Integer> pairList = new ArrayList<>();*/
        List<DishPair> dishPairList = new ArrayList<>();
        String sqlQuery = "SELECT id_dish_1, count FROM dish_pair WHERE id_dish_2 = ? AND count>0";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_dish);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    /*pairList.add(rs.getInt("id_dish_1"));*/

                    dishPairList.add(new DishPair()
                            .setId(rs.getInt("id_dish_1"))
                            .setCount(rs.getInt("count")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sqlQuery = "SELECT id_dish_2, count FROM dish_pair WHERE id_dish_1 = ? AND count>0";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id_dish);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    /*pairList.add(rs.getInt("id_dish_2"));*/
                    dishPairList.add(new DishPair()
                            .setId(rs.getInt("id_dish_2"))
                            .setCount(rs.getInt("count")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (dishPairList.size() == 0) return null;
        return dishPairList;
    }
}
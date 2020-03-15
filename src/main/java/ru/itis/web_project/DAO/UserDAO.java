package ru.itis.web_project.DAO;

import ru.itis.web_project.models.User;
import ru.itis.web_project.utils.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private static final Connection connection = ConnectionJDBC.getConnection();


    public static Optional<User> findUserById(Integer id) {
        User user = null;
        String sqlQuery = "SELECT * from user WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = rowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public static Optional<List<User>> findUserByName(String username) {
        String sqlQuery = "SELECT * FROM user WHERE username = ? GROUP BY role_id";
        ArrayList<User> list = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, username);
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

    public static Optional<User> findUserByLoginAndPassword(String login, String password) {
        String sqlQuery = "SELECT * FROM user WHERE  login = ? AND password = ?";
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    user = rowMapper.mapRow(rs);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public static Optional<List<User>> getAllUsers() {
        ArrayList<User> list = null;
        String sqlQuery = "SELECT * FROM user ORDER BY role_id";
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

    public static boolean insertUser(User user) {
        String sqlQuery = "INSERT INTO user (login, username, password, role_id, phone_number, address, registr_date) VALUES (?,?,?,?,?,?, NOW())";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole());
            ps.setString(5, user.getPhone_number());
            ps.setString(6, user.getAddress());
            return !ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteUserById(Integer id) {
        String sqlQuery = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        String sqlQuery = "UPDATE user SET login =?, username =?,password=?,role_id=?,phone_number=?,address=? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole());
            ps.setString(5, user.getPhone_number());
            ps.setString(6, user.getAddress());
            ps.setInt(7, user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static RowMapper<User> rowMapper = row -> {
        User user = new User();
        user.setId(row.getInt("id"));
        user.setLogin(row.getString("login"));
        user.setName(row.getString("username"));
        user.setRole(row.getInt("role_id"));
        user.setDate(row.getDate("registr_date"));
        user.setPhone_number(row.getString("phone_number"));
        user.setAddress(row.getString("address"));
        user.setPassword(row.getString("password"));
        return user;
    };
}

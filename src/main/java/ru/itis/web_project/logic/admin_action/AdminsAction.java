package ru.itis.web_project.logic.admin_action;

import ru.itis.web_project.DAO.RoleDAO;
import ru.itis.web_project.DAO.UserDAO;
import ru.itis.web_project.models.User;
import ru.itis.web_project.utils.HashPassword;

import java.util.List;

public class AdminsAction {
    public static boolean registerUser(String phoneNumber, String username, String login, String password, String address, String role) {
        User user = new User();
        user.setPhone_number(phoneNumber);
        user.setPassword(HashPassword.getHash(password));
        user.setLogin(login);
        user.setName(username);
        user.setAddress(address);
        user.setRole(RoleDAO.getIdByName(role));
        return UserDAO.insertUser(user);
    }

    public static List<User> getAllUsers() {
        List<User> users = UserDAO.getAllUsers().get();
        return users;
    }

    public static void deleteUser(String deleted_id) {
        Integer deletedId = Integer.parseInt(deleted_id);
        UserDAO.deleteUserById(deletedId);
    }
}

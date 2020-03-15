package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.RoleDAO;
import ru.itis.web_project.DAO.UserDAO;
import ru.itis.web_project.models.User;
import ru.itis.web_project.utils.HashPassword;

import javax.servlet.http.HttpServletRequest;

public class Registration {
    public static boolean registerUser(String phoneNumber, String username, String login, String password, String address) {
        User user = new User();
        user.setPhone_number(phoneNumber);
        user.setPassword(HashPassword.getHash(password));
        user.setLogin(login);
        user.setName(username);
        user.setAddress(address);
        user.setRole(2);
        return UserDAO.insertUser(user);
    }

    public static boolean registerWorker(HttpServletRequest request) {
        User user = new User();
        user.setPhone_number(request.getParameter("phoneNumber"));
        user.setPassword(HashPassword.getHash(request.getParameter("password")));
        user.setLogin(request.getParameter("login"));
        user.setName(request.getParameter("username"));
        user.setAddress(request.getParameter("address"));
        user.setRole(RoleDAO.getIdByName(request.getParameter("role")));
        return UserDAO.insertUser(user);
    }
}

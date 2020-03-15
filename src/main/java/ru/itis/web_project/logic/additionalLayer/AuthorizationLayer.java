package ru.itis.web_project.logic.additionalLayer;

import ru.itis.web_project.DAO.AntipathyDAO;
import ru.itis.web_project.DAO.UserDAO;
import ru.itis.web_project.utils.HashPassword;
import ru.itis.web_project.models.User;

import java.util.Optional;
import java.util.Set;

public class AuthorizationLayer {

    public static Optional<User> getUserByLoginAndPassword(String login, String password) {
        return UserDAO.findUserByLoginAndPassword(login, HashPassword.getHash(password));
    }

    public static Optional<User> getUserById(Integer userId){
        return UserDAO.findUserById(userId);
    }

    public static Set<Integer> getUserAntipathy(Integer user_id){
        return AntipathyDAO.getUsersAntipathy(user_id);
    }
}

package ru.itis.web_project.logic.additionalLayer;

import ru.itis.web_project.DAO.UserDAO;
import ru.itis.web_project.models.User;

public class EditProfileLayer {
    public static void editMainInfo(User user) {
        UserDAO.updateUser(user);
    }

    public static void checkIsCorrectOldPasswordAndUpdate(User user){
        UserDAO.updateUser(user);
    }
}

package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.UserDAO;
import ru.itis.web_project.logic.additionalLayer.EditProfileLayer;
import ru.itis.web_project.models.User;
import ru.itis.web_project.utils.HashPassword;

import javax.servlet.http.HttpServletRequest;

public class EditProfile {
    public static void editMainInfo(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        currentUser.setName(request.getParameter("editName"));
        currentUser.setPhone_number(request.getParameter("editPhoneNumber"));
        currentUser.setAddress(request.getParameter("editAddress"));
        currentUser.setLogin(request.getParameter("editLogin"));
        request.getSession().setAttribute("user", currentUser);
        EditProfileLayer.editMainInfo(currentUser);
    }

    public static boolean editPassword(HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        return checkIsCorrectOldPasswordAndUpdate(oldPassword, newPassword, request);
    }

    private static boolean checkIsCorrectOldPasswordAndUpdate(String oldPass, String newPass, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        if (HashPassword.getHash(oldPass).equals(currentUser.getPassword())) {
            currentUser.setPassword(HashPassword.getHash(newPass));
            request.getSession().setAttribute("user", currentUser);
            EditProfileLayer.checkIsCorrectOldPasswordAndUpdate(currentUser);
            return true;
        } else {
            return false;
        }

    }
}

package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.RoleDAO;

import java.util.List;

public class RoleService {
    public static List<String> getRoleNames() {
        return RoleDAO.getRoleNames();
    }

    public static String getRoleNameByID(Integer id){
        return RoleDAO.getNameById(id);
    }
}

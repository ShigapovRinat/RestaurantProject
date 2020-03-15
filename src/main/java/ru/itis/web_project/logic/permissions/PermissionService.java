package ru.itis.web_project.logic.permissions;

import ru.itis.web_project.DAO.access.ActionsDAO;
import ru.itis.web_project.DAO.access.PermissionDAO;

import java.util.List;

public class PermissionService {
    public static List<Integer> setPermissionForUser(Integer id_role) {
        return PermissionDAO.getActionsByRoleId(id_role).orElse(null);
    }

/*    public static List<Integer> getUserPermission() {
        return PermissionDAO.getActionsByRoleId(2).orElse(null);
    }

    public static List<Integer> getAdminPermission() {
        return PermissionDAO.getActionsByRoleId(1).orElse(null);
    }

    public static List<Integer> getKitchenPermission() {
        return PermissionDAO.getActionsByRoleId(4).orElse(null);
    }*/

    public static boolean haveAccess(String action, List<Integer> permissionList) {
        Integer actionId = ActionsDAO.getActionIDbyAction(action);
        if (permissionList != null) {
            for (Integer i : permissionList) {
                if (actionId == i) return true;
            }
        }
        return false;
    }

}

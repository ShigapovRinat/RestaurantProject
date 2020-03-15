package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.AntipathyDAO;
import ru.itis.web_project.DAO.DishDAO;
import ru.itis.web_project.models.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlackList {
    public static List<Dish> getList(Integer user_id) {
        Set<Integer> antipathy = AntipathyDAO.getUsersAntipathy(user_id);
        List<Dish> blacklist = new ArrayList<>();
        for (Integer integer : antipathy) {
            blacklist.add(DishDAO.getDishById(integer).get());
        }
        return blacklist.size() > 0 ? blacklist : null;
    }

    public static void deleteFromBlackList(Integer user_id,  Integer dish_id, Set<Integer> antipathy) {
        /*Set<Integer> antipathy = ((HashSet<Integer>) request.getSession(false).getAttribute("userAntipathySet"));
        Integer dish_id = Integer.parseInt(request.getParameter("dish_id"));*/
        antipathy.remove(dish_id);
        AntipathyDAO.delete(user_id, dish_id);
    }

    public static void deleteAllFromBlackList(Integer user_id) {
        AntipathyDAO.deleteAll(user_id);
    }
}

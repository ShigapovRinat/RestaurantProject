package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.DishCategoryDAO;
import ru.itis.web_project.DAO.DishDAO;
import ru.itis.web_project.models.Dish;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class MenuService {
    public static List<Dish> getMenuList() {
        Optional<List<Dish>> listOptional = DishDAO.getAllDishes();
        if (listOptional.isPresent()) {
            List<Dish> list = listOptional.get();
            /*request.setAttribute("menuList", list);*/
            return list;
        }
        return null;
    }

    public static List<String> getCategoryList(){
        return DishCategoryDAO.getAllCategories();
    }
}

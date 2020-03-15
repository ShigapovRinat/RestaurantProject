package ru.itis.web_project.logic.additionalLayer;

import ru.itis.web_project.logic.delivery_utils.TableObjectModel;
import ru.itis.web_project.logic.user_action.DishesService;
import ru.itis.web_project.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DishesServiceLayer {
    public static void toBasket(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        Integer id_menu = Integer.parseInt(request.getParameter("id_menu"));
        Integer id_count_menu = Integer.parseInt(request.getParameter("id_count_menu"));

        List<TableObjectModel> orderList = (ArrayList<TableObjectModel>) session.getAttribute("orderDeliveryList");
        Integer totalPriceFromBasket = (Integer) session.getAttribute("totalPriceFromBasket");
        Set<Integer> dishIdSet = ((HashSet<Integer>) session.getAttribute("dishIdSet"));

        if (orderList == null) {
            orderList = new ArrayList<>();
            session.setAttribute("orderDeliveryList", orderList);
        }
        if (dishIdSet == null) {
            dishIdSet = new HashSet<>();
            session.setAttribute("dishIdSet", dishIdSet);
        }

        DishesService.toBasket(id_menu, id_count_menu, orderList, dishIdSet, totalPriceFromBasket);

        session.setAttribute("totalPriceFromBasket", totalPriceFromBasket);
        session.setAttribute("orderDeliveryList", orderList);
    }

    public static boolean buyFromBasket(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<TableObjectModel> orderList = (ArrayList) session.getAttribute("orderDeliveryList");
        User user = (User) session.getAttribute("user");

        if (orderList == null) {
            return false; //ваша корзина пуста
        }

        DishesService.buyFromBasket(user, orderList);

        session.setAttribute("orderDeliveryList", null);
        session.setAttribute("dishIdSet", null);
        session.setAttribute("totalPriceFromBasket", 0);
        return true;
    }

    public static void deleteDishFromBasket(HttpServletRequest request) {
        Integer deleted_id = Integer.parseInt(request.getParameter("deleted_id"));
        Integer count_deleted_id = Integer.parseInt(request.getParameter("deleted_count_id"));

        HttpSession session = request.getSession(false);
        List<TableObjectModel> orderList = (ArrayList) session.getAttribute("orderDeliveryList");
        Set<Integer> dishIdSet = ((HashSet<Integer>) session.getAttribute("dishIdSet"));
        Integer totalPriceFromBasket = (Integer) session.getAttribute("totalPriceFromBasket");

        totalPriceFromBasket = DishesService.deleteDishFromBasket(deleted_id, count_deleted_id, orderList, dishIdSet, totalPriceFromBasket);


    }
}

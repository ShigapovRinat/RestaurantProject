package ru.itis.web_project.logic.user_action;

import ru.itis.web_project.DAO.AntipathyDAO;
import ru.itis.web_project.DAO.DeliveryOrderDAO;
import ru.itis.web_project.DAO.DishDAO;
import ru.itis.web_project.DAO.DishPairDAO;
import ru.itis.web_project.models.DeliverOrder;
import ru.itis.web_project.models.Dish;
import ru.itis.web_project.models.User;
import ru.itis.web_project.logic.delivery_utils.TableObjectModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

public class DishesService {

    public static void toBasket(Integer id_menu, Integer id_count_menu, List<TableObjectModel> orderList, Set<Integer> dishIdSet, Integer totalPriceFromBasket) {
/*        HttpSession session = request.getSession(false);

        List<TableObjectModel> orderList = (ArrayList<TableObjectModel>) session.getAttribute("orderDeliveryList");
        User user = (User) session.getAttribute("user");
        Integer totalPriceFromBasket = (Integer) session.getAttribute("totalPriceFromBasket");

        Set<Integer> dishIdSet = ((HashSet<Integer>) session.getAttribute("dishIdSet"));
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        if (dishIdSet == null) {
            dishIdSet = new HashSet<>();
            session.setAttribute("dishIdSet", dishIdSet);
        }


        Integer id_menu = Integer.parseInt(request.getParameter("id_menu"));
        Integer id_count_menu = Integer.parseInt(request.getParameter("id_count_menu"));*/
        Dish dish = DishDAO.getDishById(id_menu).get();
        TableObjectModel tableObject = new TableObjectModel();
        tableObject.setCount_id_menu(id_count_menu);
        tableObject.setId_menu(id_menu);
        tableObject.setName_dish(dish.getName());
        tableObject.setPrice(dish.getPrice());
        tableObject.setDate(new Date(System.currentTimeMillis()));
        totalPriceFromBasket = totalPriceFromBasket + dish.getPrice() * id_count_menu;

        boolean tag = false;
        for (TableObjectModel tableObj : orderList) {
            if (tableObj.getId_menu() == tableObject.getId_menu() && tableObj.getCount_id_menu() == tableObject.getCount_id_menu()) {
                tableObj.setCount_id_menu(tableObj.getCount_id_menu() + tableObject.getCount_id_menu());
                tag = true;
                break;
            }
        }
        if (!tag) {
            orderList.add(tableObject);
            dishIdSet.add(id_menu);
        }
/*        session.setAttribute("totalPriceFromBasket", totalPriceFromBasket);
        session.setAttribute("orderDeliveryList", orderList);*/
    }

    public static boolean buyFromBasket(User user, List<TableObjectModel> orderList) {
        /*
        HttpSession session = request.getSession();

        List<TableObjectModel> orderList = (ArrayList) session.getAttribute("orderDeliveryList");
        User user = (User) session.getAttribute("user");

        if (orderList == null) {
            return false; //ваша корзина пуста
        }*/
        TableObjectModel[] array = new TableObjectModel[orderList.size()];
        array = orderList.toArray(array);

        for (TableObjectModel tableObject : orderList) {
            DeliverOrder deliverOrder = new DeliverOrder();
            deliverOrder.setCount_id_menu(tableObject.getCount_id_menu());
            deliverOrder.setId_menu(tableObject.getId_menu());
            deliverOrder.setId_user(user.getId());
            DeliveryOrderDAO.insertDeliveryOrder(deliverOrder);
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].getId_menu() < array[j].getId_menu()) {
                    DishPairDAO.updatePair(array[i].getId_menu(), array[j].getId_menu());
                } else if (array[i].getId_menu() > array[j].getId_menu()) {
                    DishPairDAO.updatePair(array[j].getId_menu(), array[i].getId_menu());
                }
            }
        }
        /*session.setAttribute("orderDeliveryList", null);
        session.setAttribute("dishIdSet", null);
        session.setAttribute("totalPriceFromBasket", 0);*/
        return true;
    }

    public static Integer deleteDishFromBasket(Integer deleted_id, Integer count_deleted_id, List<TableObjectModel> orderList, Set<Integer> dishIdSet, Integer totalPriceFromBasket) {
        /*Integer deleted_id = Integer.parseInt(request.getParameter("deleted_id"));
        Integer count_deleted_id = Integer.parseInt(request.getParameter("deleted_count_id"));

        HttpSession session = request.getSession(false);
        List<TableObjectModel> orderList = (ArrayList) session.getAttribute("orderDeliveryList");
        Set<Integer> dishIdSet = ((HashSet<Integer>) session.getAttribute("dishIdSet"));*/
        for (TableObjectModel tableObject : orderList) {
            if (tableObject.getId_menu().equals(deleted_id) && tableObject.getCount_id_menu().equals(count_deleted_id)) {
                orderList.remove(tableObject);
                dishIdSet.remove(deleted_id);
                return totalPriceFromBasket - tableObject.getPrice() * tableObject.getCount_id_menu();
                /*session.setAttribute("totalPriceFromBasket", (Integer) session.getAttribute("totalPriceFromBasket") - tableObject.getPrice() * tableObject.getCount_id_menu());
                break;*/
            }
        }
        return totalPriceFromBasket;
    }

    public static void updateUserAntipathy(boolean isDelete, HashSet<Integer> userAntipathy, int dish_id, int user_id) {
        if (isDelete) {
            userAntipathy.remove(dish_id);
            AntipathyDAO.delete(user_id, dish_id);
        } else {
            userAntipathy.add(dish_id);
            AntipathyDAO.insert(user_id, dish_id);
        }
    }

}

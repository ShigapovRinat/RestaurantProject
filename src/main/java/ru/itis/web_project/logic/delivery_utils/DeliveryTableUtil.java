package ru.itis.web_project.logic.delivery_utils;

import ru.itis.web_project.DAO.DeliveryOrderDAO;
import ru.itis.web_project.DAO.DishDAO;
import ru.itis.web_project.models.DeliverOrder;
import ru.itis.web_project.models.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryTableUtil {

    public static List<TableObjectModel> getTableDeliveryOrder(Integer user_id) {
        ArrayList<DeliverOrder> arrayList = null;
        Optional<List<DeliverOrder>> pseudoList = DeliveryOrderDAO.getAllDeliveryOrdersByIdUser(user_id);
        if (!pseudoList.isPresent()) {
            return null;
        } else {
            arrayList = (ArrayList<DeliverOrder>) pseudoList.get();
            List<TableObjectModel> newList = new ArrayList<>();

            for (DeliverOrder delivery : arrayList) {
                Dish dish = DishDAO.getDishById(delivery.getId_menu()).get();
                TableObjectModel tableObject = new TableObjectModel();
                tableObject.setCount_id_menu(delivery.getCount_id_menu());
                tableObject.setDate(delivery.getDate());
                tableObject.setName_dish(dish.getName());
                tableObject.setPrice(dish.getPrice());
                tableObject.setId_menu(dish.getId());
                newList.add(tableObject);
            }
            return newList;
        }
    }
}

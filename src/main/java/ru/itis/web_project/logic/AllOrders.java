package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.DeliveryOrderDAO;
import ru.itis.web_project.models.DeliverOrder;

import java.util.List;

public class AllOrders {
    public static List<DeliverOrder> showAllOrders() {
        List<DeliverOrder> list = DeliveryOrderDAO.showAllDeliverOrders();

        return list;
    }
}

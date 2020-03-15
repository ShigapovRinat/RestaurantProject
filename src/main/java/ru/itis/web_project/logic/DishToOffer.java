package ru.itis.web_project.logic;

import ru.itis.web_project.DAO.DishDAO;
import ru.itis.web_project.DAO.DishPairDAO;
import ru.itis.web_project.models.Dish;
import ru.itis.web_project.models.DishPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class DishToOffer {
    public static List<Dish> makeOffer(Set<Integer> usersAntipathySet, Set<Integer> dishIdFromBasketSet) {
/*
        HttpSession session = request.getSession(false);
        Set<Integer> usersAntipathySet = (HashSet<Integer>) session.getAttribute("userAntipathySet");
        Set<Integer> dishIdFromBasketSet = (HashSet<Integer>) session.getAttribute("dishIdSet");
*/
        List<Dish> dishListForOffer = new ArrayList<>();
        List<Integer> dishIdFromBasketList = new ArrayList<>(dishIdFromBasketSet);

        dishIdFromBasketList.sort(Comparator.comparingInt(x -> x));

        for (Integer integer : dishIdFromBasketList) {
            /*List<Integer> allPairs = DishPairDAO.getAllPairs(integer);

            allPairs.sort(Comparator.comparingInt(x -> x));
            for (Integer allPair : allPairs) {
                if (!usersAntipathySet.contains(allPair) && !dishIdFromBasketSet.contains(allPair)) {
                    dishListForOffer.add(DishDAO.getDishById(allPair).get());
                }
            }*/
            List<DishPair> dishPairs = DishPairDAO.getAllPairs(integer);
            if (dishPairs != null) {
                dishPairs.sort((o1, o2) -> o2.getCount() - o1.getCount());
                int i = 0;
                for (DishPair dishPair : dishPairs) {
                    if (!usersAntipathySet.contains(dishPair.getId()) && !dishIdFromBasketSet.contains(dishPair.getId())) {
                        i++;
                        dishListForOffer.add(DishDAO.getDishById(dishPair.getId()).get());
                    }
                    if (i == 3) break;
                }
            }
        }

        return dishListForOffer.size() > 0 ? dishListForOffer : null;
        //найти все пары
    }
}

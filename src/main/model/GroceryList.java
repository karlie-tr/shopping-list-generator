package model;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {

    private List<Item> foodToBuy;

    public GroceryList() {
        foodToBuy = new ArrayList<>();
    }

    public boolean lookUpItemByName(String foodName) {
        return false;
    }

    public void addToGroceryList(Item item) {
        foodToBuy.add(item);
    }
}

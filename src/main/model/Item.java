package model;

// Represent a food item with its name and quantity

public class Item {

    private String name;               // represent the name of the food item
    private Integer quantity;          // positive non-zero integer that represent the amount needed to buy

    // REQUIRES: foodQuantity >= 1
    // EFFECT: create a new item with its name and a quantity
    public Item(String foodName, Integer foodQuantity) {
        name = foodName;
        quantity = foodQuantity;
    }

    // EFFECT: if an item has already in the list, add increase quantity by 1;
    //         if an item is not in the list, add them to the list.
    public void addItem(GroceryList foodToBuy, Item item) {
        Item newItem;

        if (foodToBuy.lookUpItemByName(item.getItemName())) {
            newItem = increaseQuantityByOne();
            foodToBuy.addToGroceryList(newItem);
        }
        foodToBuy.addToGroceryList(item);
    }

    // EFFECT: create a new increase an item's quantity by one
    private Item increaseQuantityByOne() {
        Integer newQuantity = getQuantity() + 1;
        Item itemWithNewQuantity = new Item(name, newQuantity);
        return itemWithNewQuantity;
    }

    // getters
    private int getQuantity() {
        return quantity;
    }

    // getters
    private String getItemName() {
        return name;
    }


}
